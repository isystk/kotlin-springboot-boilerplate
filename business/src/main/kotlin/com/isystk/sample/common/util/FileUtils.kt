package com.isystk.sample.common.util

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.FileAlreadyExistsException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import javax.imageio.ImageIO
import javax.imageio.stream.ImageInputStream

object FileUtils {
    /**
     * ディレクトリがない場合は作成します。
     *
     * @param location
     */
    @JvmStatic
    fun createDirectory(location: Path) {
        try {
            Files.createDirectory(location)
        } catch (ignore: FileAlreadyExistsException) {
            // ignore
        } catch (e: IOException) {
            throw IllegalArgumentException("could not create directory. $location", e)
        }
    }

    /**
     * 親ディレクトリを含めてディレクトリがない場合は作成します。
     *
     * @param location
     */
    @JvmStatic
    fun createDirectories(location: Path) {
        try {
            Files.createDirectories(location)
        } catch (ignore: FileAlreadyExistsException) {
            // ignore
        } catch (e: IOException) {
            throw IllegalArgumentException("could not create directory. $location", e)
        }
    }

    /**
     * ファイルの拡張子をチェックします。
     *
     * @param file       ファイル
     * @param extensions 対応する拡張子
     * @return true / false
     */
    @JvmStatic
    fun isExtension(file: File?, extensions: Array<String?>?): Boolean {
        if (file == null) {
            return false
        }
        val fileName = file.name
        if (extensions == null || extensions.size == 0) {
            return FilenameUtils.indexOfExtension(fileName) == -1
        }
        val fileExt = getExtension(fileName)
        for (i in extensions.indices) {
            if (fileExt.equals(extensions[i], ignoreCase = true)) {
                return true
            }
        }
        return false
    }

    /**
     * ファイルの拡張子を取得します。
     *
     * @param fileName ファイル
     */
    @JvmStatic
    fun getExtension(fileName: String?): String {
        if (StringUtils.isBlankOrSpace(fileName)) {
            return ""
        }
        val fileExt = FilenameUtils.getExtension(fileName)
        return fileExt.lowercase(Locale.getDefault())
    }

    /**
     * ファイルサイズをチェックします。
     *
     * @param file    ファイル
     * @param maxSize 最大のファイルサイズ（バイト）
     * @return true / false
     */
    @JvmStatic
    fun maxFileSize(file: File?, maxSize: Long): Boolean {
        if (file == null) {
            return false
        }
        return maxSize >= file.length()
    }

    /**
     * 画像ファイルかどうかをチェックします。
     *
     * @param imageFile 画像ファイル
     * @return true / false
     */
    @JvmStatic
    fun isImageFile(imageFile: File?): Boolean {
        if (imageFile == null) {
            return false
        }
        var `is`: ImageInputStream? = null
        try {
            `is` = ImageIO.createImageInputStream(FileInputStream(imageFile))
            val i = ImageIO.getImageReaders(`is`)
            while (i.hasNext()) {
                return true
            }
        } catch (e: IOException) {
        } finally {
            if (`is` != null) {
                try {
                    `is`.close()
                } catch (e1: IOException) {
                }
            }
        }
        return false
    }

    /**
     * ファイルまたはフォルダを削除する
     *
     * @param path パス
     * @return true=削除成功、false=削除失敗
     */
    @JvmStatic
    fun delete(path: String?): Boolean {
        if (StringUtils.isNullOrEmpty(path)) {
            return false
        }
        val file = File(path)
        return delete(file)
    }

    /**
     * ファイルまたはフォルダを削除する
     *
     * @param file java.io.File
     * @return true=削除成功、false=削除失敗
     */
    @JvmStatic
    fun delete(file: File?): Boolean {
        if (file == null) {
            return false
        }
        return if (!file.exists()) {
            false
        } else try {
            FileUtils.forceDelete(file)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
}