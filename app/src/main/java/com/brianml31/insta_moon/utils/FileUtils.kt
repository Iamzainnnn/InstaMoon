package com.brianml31.insta_moon.utils

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileUtils {
    companion object{
        fun exportDevSettingsV2(ctx: Context) {
            if (!PermissionsUtils.checkPermission(ctx)) {
                PermissionsUtils.requestPermission(ctx)
            } else {
                try {
                    val fileMCOverrides = File(ctx.filesDir, "mobileconfig" + File.separator + "mc_overrides.json")
                    if (fileMCOverrides.exists()) {
                        DialogUtils.showFileNameDialog(ctx, fileMCOverrides)
                    } else {
                        ToastUtils.showShortToast(ctx, "There is no configuration file to export")
                    }
                } catch (e: Exception) {
                    ToastUtils.showShortToast(ctx, "Error: Could not export developer mode settings")
                }
            }
        }

        fun importDevSettings(activity: Activity, uri: Uri?) {
            try {
                val inputStream = activity.contentResolver.openInputStream(uri!!)
                val fileMCOverrides = File(activity.filesDir, "mobileconfig" + File.separator + "mc_overrides.json")
                if (!fileMCOverrides.exists()) {
                    fileMCOverrides.createNewFile()
                }
                val fileOutputStream = FileOutputStream(fileMCOverrides)
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while ((inputStream!!.read(buffer).also { bytesRead = it }) > 0) {
                    fileOutputStream.write(buffer, 0, bytesRead)
                }
                ToastUtils.showShortToast(activity, "Settings imported successfully")
                inputStream.close()
                fileOutputStream.close()
                DialogUtils.showRestartAppDialog(activity)
            } catch (e: Exception) {
                ToastUtils.showShortToast(activity, "Error: Could not import developer mode settings")
            }
        }

        fun copyStream(fileInput: File?, fileOutput: File?) {
            try {
                val fileInputStream = FileInputStream(fileInput)
                val fileOutputStream = FileOutputStream(fileOutput)
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while ((fileInputStream.read(buffer).also { bytesRead = it }) > 0) {
                    fileOutputStream.write(buffer, 0, bytesRead)
                }
                fileInputStream.close()
                fileOutputStream.close()
            } catch (e: Exception) {
            }
        }

        fun deleteMCOverrides(ctx: Context): Boolean {
            try {
                val file_mc_overrides = File(ctx.filesDir, "mobileconfig" + File.separator + "mc_overrides.json")
                if (file_mc_overrides.exists()) {
                    return file_mc_overrides.delete()
                }
                return false
            } catch (e: Exception) {
                return false
            }
        }

        fun saveFileIdNameMapping(ctx: Context) {
            if (!PermissionsUtils.checkPermission(ctx)) {
                PermissionsUtils.requestPermission(ctx)
            } else {
                try {
                    val fileIdNameMapping = File(ctx.filesDir, "mobileconfig" + File.separator + "id_name_mapping.json")
                    if (fileIdNameMapping.exists()) {
                        val directoryOutput = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Constants.ID_NAME_MAPPING_OUTPUT_FOLDER)
                        if (!directoryOutput.exists()) {
                            directoryOutput.mkdirs()
                        }
                        val fileOutput = File(directoryOutput, "id_name_mapping_" + Utils.getVersionName(ctx) + ".json")
                        if (!fileOutput.exists()) {
                            fileOutput.createNewFile()
                        }
                        copyStream(fileIdNameMapping, fileOutput)
                        ToastUtils.showShortToast(ctx, "File saved in" + fileOutput.path)
                    } else {
                        ToastUtils.showShortToast(ctx, "The file (id_name_mapping.json) does not exist")
                    }
                } catch (e: Exception) {
                    ToastUtils.showShortToast(ctx, "An error occurred while importing the file \"id name mapping.json\"")
                }
            }
        }

    }
}