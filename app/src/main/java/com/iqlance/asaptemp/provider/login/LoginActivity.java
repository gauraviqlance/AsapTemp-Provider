package com.iqlance.asaptemp.provider.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.iqlance.asaptemp.provider.MainActivity;
import com.iqlance.asaptemp.provider.R;
import com.iqlance.asaptemp.provider.forgotPassword.ForgotPasswordActivity;
import com.iqlance.asaptemp.provider.signUp.SignUpActivity;
import com.iqlance.asaptemp.provider.signUp.UserModel;
import com.iqlance.asaptemp.provider.utils.CommonUtils;
import com.iqlance.asaptemp.provider.utils.Preference;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.linearSignUpLogin)
    LinearLayout linearSignUpLogin;
    @BindView(R.id.txtInputEmailAddressLogin)
    TextInputLayout txtInputEmailAddressLogin;
    @BindView(R.id.txtInputPasswordLogin)
    TextInputLayout txtInputPasswordLogin;
    @BindView(R.id.edtEmailAddressLogin)
    TextInputEditText edtEmailAddressLogin;
    @BindView(R.id.edtPasswordLogin)
    TextInputEditText edtPasswordLogin;
    @BindView(R.id.progressBarLogin)
    ProgressBar progressBarLogin;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.txtForgotPasswordLogin)
    TextView txtForgotPasswordLogin;
    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        preference = new Preference(LoginActivity.this);
    }


    private void gotoLogin() {

        updateLoader(true);
        resetError();

        String emailId = Objects.requireNonNull(edtEmailAddressLogin.getText()).toString().trim();
        String password = Objects.requireNonNull(edtPasswordLogin.getText()).toString();

        if (TextUtils.isEmpty(emailId)) {
            txtInputEmailAddressLogin.setError(getResources().getString(R.string.email_address_error));
            txtInputEmailAddressLogin.requestFocus();
            updateLoader(false);
            return;
        } else if (!CommonUtils.isValidMail(emailId)) {
            txtInputEmailAddressLogin.setError(getResources().getString(R.string.email_address_validate_error));
            txtInputEmailAddressLogin.requestFocus();
            updateLoader(false);
            return;
        } else if (TextUtils.isEmpty(password)) {
            txtInputPasswordLogin.setError(getResources().getString(R.string.password_error));
            txtInputPasswordLogin.requestFocus();
            updateLoader(false);
            return;
        } else {
            updateLoader(false);

            int userId = 0;
            userId++;

            UserModel userModel = new UserModel();


            userModel.setUserId(String.valueOf(userId));
            //userModel.setUserName(userName);
            userModel.setUserPassword(password);

            preference.createLoginSession(userModel);

            gotoMainActivity();
        }
    }


    private void resetError() {
        txtInputEmailAddressLogin.setError(null);
        txtInputPasswordLogin.setError(null);
    }

    private void updateLoader(boolean b) {

        if (progressBarLogin == null) return;

        if (b) {
            btnLogin.setVisibility(View.GONE);
            progressBarLogin.setVisibility(View.VISIBLE);
        } else {
            progressBarLogin.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }

    }

    private void gotoForgotPassword() {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);

    }

    private void gotoSignUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @OnClick({R.id.btnLogin, R.id.linearSignUpLogin, R.id.txtForgotPasswordLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                gotoLogin();
                break;
            case R.id.linearSignUpLogin:
                gotoSignUpActivity();
                break;
            case R.id.txtForgotPasswordLogin:
                gotoForgotPassword();
                break;
        }

    }

}
