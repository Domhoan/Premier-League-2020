package com.example.premier_league.ui.setting

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import com.example.premier_league.R
import com.example.premier_league.base.BaseFragment
import kotlinx.android.synthetic.main.frament_setting.*


class SettingFragment : BaseFragment(), SettingContract.View {

    private var presenter: SettingPresenter? = null

    override val layoutResource: Int
        get() = R.layout.frament_setting

    override fun initData() {
        presenter = SettingPresenter(this)
        presenter?.start()
        feedBack(getString(R.string.title_feedback))
        shareWithFriend(
            getString(R.string.message_feedback), Uri.parse(
                getString(R.string.uri_feedback)
            )
        )
        showAboutApp()
    }

    override fun showAboutApp() {
        textAboutApp.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(getString(R.string.title_about_app))
            builder.setMessage(getString(R.string.text_info_app))
            builder.setNegativeButton(
                getString(R.string.title_yes)
            ) { dialog, _ -> dialog.cancel() }
            val al: AlertDialog = builder.create()
            al.show()
        }
    }

    override fun shareWithFriend(message: String, attachment: Uri) {
        textShareFriend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data =
                Uri.parse(getString(R.string.uri_sms_to));  // This ensures only SMS apps respond
            intent.putExtra(SMS_BODY, message);
            intent.putExtra(Intent.EXTRA_STREAM, attachment);
            if (context?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
                startActivity(intent)
            }
        }
    }

    override fun feedBack(subject: String?) {
        textFeedback.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse(getString(R.string.uri_mail_to))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            if (context?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
                startActivity(intent)
            }
        }
    }

    override fun changeLanguage() {}

    companion object {
        private const val SMS_BODY = "sms_body"
    }
}
