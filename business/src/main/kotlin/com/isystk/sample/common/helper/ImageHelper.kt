package com.isystk.sample.common.helper

import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.S3Object
import com.amazonaws.services.s3.model.S3ObjectSummary
import com.isystk.sample.common.util.AwsS3Utils
import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.util.Assert
import java.awt.image.BufferedImage
import java.io.*
import java.util.*
import javax.imageio.ImageIO

/**
 * 画像ヘルパー
 */
@Component("img")
class ImageHelper {
    @Value("\${application.imageUploadLocation:#{systemProperties['java.io.tmpdir']}}")
    var imageUploadLocation: // 設定ファイルに定義されたアップロード先を取得する
            String? = null

    /**
     * ファイルを保存します。
     *
     * @param imageData 画像データ
     * @param dirPath ディレクトリパス
     * @param upFileName 画像ファイル名
     */
    fun saveFileData(imageData: String, dirPath: String, upFileName: String, delete: Boolean) {
        Assert.notNull(imageData, "imageData can't be null")
        try {
            // 画像ファイルをデコード
            val decodedBytes = Base64.getDecoder().decode(imageData.substring(imageData.indexOf(',') + 1))
            val imageFilePath = (imageUploadLocation + System.getProperty("file.separator")
                    + upFileName)
            FileUtils.writeByteArrayToFile(File(imageFilePath), decodedBytes)
            saveFile(imageFilePath, dirPath, upFileName, delete)
        } catch (e: IOException) {
            throw IllegalStateException("failed to save file. $upFileName", e)
        }
    }

    /**
     * ファイルを保存します。
     *
     * @param imageFilePath 画像データ
     * @param dirPath ディレクトリパス
     * @param upFileName 画像ファイル名
     */
    fun saveFile(imageFilePath: String, dirPath: String, upFileName: String, delete: Boolean) {
        Assert.notNull(imageFilePath, "imageFile can't be null")
        val remotePath = "$dirPath/$upFileName"
        // S3に保存
        AwsS3Utils.Companion.s3PutObject(imageFilePath, remotePath, BUCKET_NAME, delete)
    }

    /**
     * ファイルを削除します。
     *
     * @param dirPath ディレクトリパス
     * @param upFileName 画像ファイル名
     */
    fun removeFile(dirPath: String, upFileName: String) {
        val remotePath = "$dirPath/$upFileName"
        // S3から削除
        AwsS3Utils.Companion.s3DeleteObject(BUCKET_NAME, remotePath)
    }

    fun getImageList(dirPath: String): List<String> {
        return try {
            // S3を探索
            val list: List<S3ObjectSummary> = AwsS3Utils.Companion.s3GetListsObject(BUCKET_NAME, dirPath)
            list.map { e: S3ObjectSummary -> e.key }
        } catch (e: AmazonS3Exception) {
            throw RuntimeException(e)
        }
    }

    fun getImageData(dirPath: String, upFileName: String): String? {
        val remotePath = "$dirPath/$upFileName"
        try {
            val exist: Boolean = AwsS3Utils.Companion.s3Exist(BUCKET_NAME, remotePath)
            if (!exist) {
                return null
            }
            // S3から画像を取得
            val s3Object: S3Object = AwsS3Utils.Companion.s3GetObject(BUCKET_NAME, remotePath)

            // Base64のデータで読み込む
            if (s3Object != null) {
                val imgBuf: BufferedImage
                imgBuf = ImageIO.read(s3Object.objectContent)
                return encodeBase64URL(imgBuf)
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: AmazonS3Exception) {
            throw RuntimeException(e)
        }
        return null
    }

    fun getImageUrl(dirPath: String, upFileName: String): String {
        val remotePath = "$dirPath/$upFileName"
        return "/thumb$remotePath"
    }

    fun encodeBase64URL(imgBuf: BufferedImage?): String? {
        var base64: String?
        if (imgBuf == null) {
            base64 = null
        } else {
            val out = ByteArrayOutputStream()
            try {
                ImageIO.write(imgBuf, "png", out)
            } catch (e: IOException) {
                base64 = null
            }
            val bytes = out.toByteArray()
            base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes)
        }
        return base64
    }

    /**
     * JSPからアクセス用
     */
    fun url(dirPath: String, imageFileName: String): String {
        return getImageUrl(dirPath, imageFileName)
    }

    fun data(dirPath: String, imageFileName: String): String? {
        return getImageData(dirPath, imageFileName)
    }

    @Value("\${aws.s3.bucket-name}")
    fun setBucketName(bucketName: String?) {
        BUCKET_NAME = bucketName
    }

    companion object {
        private val log = LoggerFactory.getLogger(ImageHelper::class.java)
        var BUCKET_NAME: String? = null
    }
}