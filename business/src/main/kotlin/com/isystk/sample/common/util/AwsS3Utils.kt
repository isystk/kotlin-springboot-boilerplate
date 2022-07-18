package com.isystk.sample.common.util

import com.amazonaws.ClientConfiguration
import com.amazonaws.SDKGlobalConfiguration
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.internal.StaticCredentialsProvider
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import com.google.common.collect.Lists
import com.isystk.sample.common.Environment
import com.isystk.sample.common.exception.SystemException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.io.*

@Configuration
class AwsS3Utils {
    @Value("\${aws.s3.endpoint-url}")
    fun setEndpointUrl(endpointUrl: String?) {
        ENDPOINT_URL = endpointUrl
    }

    @Value("\${aws.s3.region}")
    fun setRegion(region: String?) {
        REGION = region
    }

    //  @Value("${aws.s3.profile-name}")
    //  public void setProfileName(String profileName) {
    //    PROFILE_NAME = profileName;
    //  }
    @Value("\${aws.s3.aws-access-key}")
    fun setAwsAccessKey(awsAccessKey: String?) {
        AWS_ACCESS_KEY = awsAccessKey
    }

    @Value("\${aws.s3.aws-secret-key}")
    fun setAwsSecretKey(awsSecretKey: String?) {
        AWS_SECRET_KEY = awsSecretKey
    }

    companion object {
        /** ログ  */
        protected val logger = LoggerFactory.getLogger(AwsS3Utils::class.java)
        var ENDPOINT_URL: String? = null
        var REGION: String? = null

        private var AWS_ACCESS_KEY: String? = null
        private var AWS_SECRET_KEY: String? = null

        /**
         * S3クライアントを取得する
         * @return AmazonS3
         */
        private val s3Client: AmazonS3
            private get() {
                System.setProperty(SDKGlobalConfiguration.ENABLE_S3_SIGV4_SYSTEM_PROPERTY, "true")
                val clientConfig = ClientConfiguration()
                clientConfig.connectionTimeout = 30000
                clientConfig.clientExecutionTimeout = 300000
                clientConfig.socketTimeout = 30000
                clientConfig.requestTimeout = 30000
                clientConfig.maxErrorRetry = 10
                val endpointConfiguration = EndpointConfiguration(ENDPOINT_URL,
                        REGION)
                val clientBuilder = AmazonS3ClientBuilder.standard().withClientConfiguration(clientConfig)
                        .withEndpointConfiguration(endpointConfiguration)
                        .withPathStyleAccessEnabled(true)
                if (Environment.Companion.get() == Environment.LOCAL) {
                    // ローカル環境の場合のみクレディンシャルのパスを指定する
                    val credentials: AWSCredentials = BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)
                    val credentialsProvider: AWSCredentialsProvider = StaticCredentialsProvider(credentials)
                    //			ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider(CREDENTIALS, PROFILE_NAME);
                    return clientBuilder.withCredentials(credentialsProvider).build()
                }
                // AWS環境の場合はIAMロール認証なのでクレディンシャルは設定しない
                return clientBuilder.build()
            }

        /**
         * S3にファイルをアップロードします。
         * アップロード後にローカルファイルを削除します。
         * @param filePath
         * @param remotePath
         * @param bucket
         * @return
         */
        @JvmOverloads
        @JvmStatic
        fun s3PutObject(filePath: String, remotePath: String, bucket: String?, delete: Boolean = true): Boolean {
            var remotePath = remotePath
            val s3 = s3Client
            // remotePathは先頭/始まりの場合には、削除する
            remotePath = if (remotePath.startsWith("/")) remotePath.substring(1) else remotePath
            try {
                logger.info("bucket:$bucket remotePath:$remotePath localPath:$filePath")
                s3.putObject(bucket, remotePath, File(filePath))
            } catch (e: SystemException) {
                throw e
            } catch (e: Exception) {
                throw SystemException(e)
            }
            // s3アップロード完了後はファイルを削除する。
            if (delete) FileUtils.delete(filePath)
            return true
        }

        @JvmStatic
        fun s3Exist(bucket: String?, filePath: String?): Boolean {
            val s3 = s3Client
            return s3.doesObjectExist(bucket, filePath)
        }

        /**
         * 条件に当たるS3ObjectSummaryをListで返す
         * 最大1000件まで取得するため、s3GetListsObjectV2を使うこと
         *
         * @param bucket
         * @param filePath
         * @return
         */
        @JvmStatic
        fun s3GetListsObject(bucket: String?, filePath: String): List<S3ObjectSummary> {
            val s3 = s3Client
            val list = s3.listObjects(ListObjectsRequest().withBucketName(bucket)
                    .withPrefix(if (filePath.startsWith("/")) filePath.substring(1) else filePath))
            return list.objectSummaries
        }

        @JvmStatic
        fun s3GetObject(bucket: String?, filePath: String?): S3Object {
            val s3 = s3Client
            return s3.getObject(bucket, filePath)
        }

        @JvmStatic
        fun s3DeleteObject(bucket: String?, filePath: String?) {
            val s3 = s3Client
            val objects = s3.listObjects(bucket, filePath)
            for (objectSummary in objects.objectSummaries) {
                s3.deleteObject(bucket, objectSummary.key)
            }
        }

        @JvmStatic
        fun s3CopyObject(fromBucket: String?, fromObjectKey: String?, toBucket: String?, toObjectKey: String?): CopyObjectResult {
            val s3 = s3Client
            val copyObjectRequest = CopyObjectRequest(fromBucket, fromObjectKey, toBucket, toObjectKey)
            return s3.copyObject(copyObjectRequest)
        }

        /**
         * 条件に当たるS3ObjectSummaryをListで返す
         *
         * @param bucket
         * @param filePath
         * @return
         */
        @JvmStatic
        fun s3GetListsObjectV2(bucket: String?, filePath: String): List<S3ObjectSummary> {
            val s3 = s3Client
            val resultList: MutableList<S3ObjectSummary> = Lists.newArrayList()
            val request = ListObjectsV2Request()
                    .withBucketName(bucket)
                    .withPrefix(if (filePath.startsWith("/")) filePath.substring(1) else filePath)
            var result: ListObjectsV2Result
            do {
                result = s3.listObjectsV2(request)
                resultList.addAll(result.objectSummaries)
                val token = result.nextContinuationToken
                request.continuationToken = token
            } while (result.isTruncated)
            return resultList
        }
    }
}