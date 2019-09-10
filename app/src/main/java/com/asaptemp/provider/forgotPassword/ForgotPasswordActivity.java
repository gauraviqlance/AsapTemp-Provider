package com.asaptemp.provider.forgotPassword;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.asaptemp.provider.R;
import com.asaptemp.provider.utils.CommonUtils;
import com.asaptemp.provider.utils.CustomButton;
import com.asaptemp.provider.utils.CustomEditText;
import com.asaptemp.provider.utils.CustomTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity {

//    private static final int WIDTH_INDEX = 0;
//    private static final int HEIGHT_INDEX = 1;

    @BindView(R.id.toolbarForgotPassword)
    Toolbar toolbarForgotPassword;

    @BindView(R.id.txtInputEmailAddressForgotPassword)
    TextInputLayout txtInputEmailAddressForgotPassword;

    @BindView(R.id.edtEmailAddressForgotPassword)
    CustomEditText edtEmailAddressForgotPassword;

    @BindView(R.id.progressBarForgot)
    ProgressBar progressBarForgot;

    @BindView(R.id.btnSendForgotPassword)
    CustomButton btnSendForgotPassword;


    private View.OnClickListener onNavigationIconClickListener = view -> finish();


    private CustomEditText.OnEditorActionListener onEditorActionListener = (textView, i, keyEvent) -> {
        if (i == EditorInfo.IME_ACTION_DONE) {
            //Do your stuff here

            gotoSendApi();
            return true;  // mark the event as consumed
        }
        return false;
    };

    private View.OnClickListener continueClickListener = view -> gotoLoginActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbarForgotPassword);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarForgotPassword.setNavigationOnClickListener(onNavigationIconClickListener);
        }

        if (edtEmailAddressForgotPassword != null) {
            edtEmailAddressForgotPassword.setOnEditorActionListener(onEditorActionListener);
        }


    }

    private void gotoSendApi() {

        updateLoader(true);
        resetError();
        String emailId = Objects.requireNonNull(edtEmailAddressForgotPassword.getText()).toString().trim();

        if (TextUtils.isEmpty(emailId)) {
            txtInputEmailAddressForgotPassword.setError(getResources().getString(R.string.email_address_error));
            txtInputEmailAddressForgotPassword.requestFocus();
            updateLoader(false);
            return;
        } else if (!CommonUtils.isValidMail(emailId)) {
            txtInputEmailAddressForgotPassword.setError(getResources().getString(R.string.email_address_validate_error));
            txtInputEmailAddressForgotPassword.requestFocus();
            updateLoader(false);
            return;
        } else {
            updateLoader(false);
            gotoContinueActivity();
        }

    }

    private void gotoContinueActivity() {

        CommonUtils.hideKeyboard(ForgotPasswordActivity.this);

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(ForgotPasswordActivity.this, R.style.ForgotAlertTheme);
        View mView = getLayoutInflater().inflate(R.layout.alert_dialog_continue, null);
        mBuilder.setView(mView);

        ImageView imgSuccess = mView.findViewById(R.id.imgSuccess);
        CustomTextView txtTitle = mView.findViewById(R.id.txtTitle);
        CustomTextView txtMsg = mView.findViewById(R.id.txtMsg);

        ConstraintLayout constraintLayout = mView.findViewById(R.id.constrainContinue);
        if (constraintLayout != null) {
            constraintLayout.setOnClickListener(continueClickListener);
        }

        //LinearLayout rootView = mView.findViewById(R.id.linearContinue);
        AlertDialog mDialog = mBuilder.create();
        mDialog.setCanceledOnTouchOutside(true);

        Window window = mDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            window.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            window.setBackgroundDrawableResource(R.color.dialogBg);
            window.setGravity(Gravity.CENTER);
            mDialog.show();
        }

    }


    private void resetError() {
        txtInputEmailAddressForgotPassword.setError(null);
    }

    private void updateLoader(boolean b) {
        if (progressBarForgot == null) return;

        if (b) {
            btnSendForgotPassword.setVisibility(View.GONE);
            progressBarForgot.setVisibility(View.VISIBLE);
        } else {

            progressBarForgot.setVisibility(View.GONE);
            btnSendForgotPassword.setVisibility(View.VISIBLE);
        }

    }

    private void gotoLoginActivity() {
        finish();
    }

    @OnClick({R.id.btnSendForgotPassword})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btnSendForgotPassword) {
            gotoSendApi();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
