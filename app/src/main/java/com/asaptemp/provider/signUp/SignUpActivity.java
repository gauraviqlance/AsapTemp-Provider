package com.asaptemp.provider.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.asaptemp.provider.MainActivity;
import com.asaptemp.provider.R;
import com.asaptemp.provider.login.LoginActivity;
import com.asaptemp.provider.utils.CommonUtils;
import com.asaptemp.provider.utils.CustomButton;
import com.asaptemp.provider.utils.CustomEditText;
import com.asaptemp.provider.utils.Preference;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    private Preference preference;

    @BindView(R.id.linearLogin)
    LinearLayout linearLogin;

    @BindView(R.id.btnSignUp)
    CustomButton btnSignUp;

    @BindView(R.id.txtInputUserName)
    TextInputLayout txtInputUserName;
    @BindView(R.id.txtInputMobileNumber)
    TextInputLayout txtInputMobileNumber;
    @BindView(R.id.txtInputEmailAddress)
    TextInputLayout txtInputEmailAddress;
    @BindView(R.id.txtInputPassword)
    TextInputLayout txtInputPassword;

    @BindView(R.id.edtUserName)
    CustomEditText edtUserName;
    @BindView(R.id.edtMobileNumber)
    CustomEditText edtMobileNumber;
    @BindView(R.id.edtEmailAddress)
    CustomEditText edtEmailAddress;
    @BindView(R.id.edtPassword)
    CustomEditText edtPassword;
    @BindView(R.id.progressBarSignUp)
    ProgressBar progressBarSignUp;

//    @BindView(R.id.filled_exposed_dropdown)
//    AutoCompleteTextView filled_exposed_dropdown;
//
//
//
//
//    String[] COUNTRIES = new String[] {"Country","India", "Indonesia", "Canada", "USA"};
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        preference = new Preference(SignUpActivity.this);

//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<>(
//                        SignUpActivity.this,
//                        R.layout.dropdown_menu_popup_item,
//                        COUNTRIES);
//
//        filled_exposed_dropdown.setAdapter(adapter);
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);

    }


    private void gotoSignUp() {

        updateLoader(true);
        resetError();

        String userName = edtUserName.getText().toString();
        String mobileNumber = edtMobileNumber.getText().toString();
        String emailId = edtEmailAddress.getText().toString();
        String password = edtPassword.getText().toString();

        if(TextUtils.isEmpty(userName)){
            txtInputUserName.setError(getResources().getString(R.string.user_name_error));
            txtInputUserName.requestFocus();
            updateLoader(false);
            return;
        }
        else if(TextUtils.isEmpty(mobileNumber)){
            txtInputMobileNumber.setError(getResources().getString(R.string.mobile_number_error));
            txtInputMobileNumber.requestFocus();
            updateLoader(false);
            return;
        }else if(!CommonUtils.isValidMobile(mobileNumber)){
            txtInputMobileNumber.setError(getResources().getString(R.string.mobile_number_validate_error));
            txtInputMobileNumber.requestFocus();
            updateLoader(false);
            return;
        }
        else if(TextUtils.isEmpty(emailId)){
            txtInputEmailAddress.setError(getResources().getString(R.string.email_address_error));
            txtInputEmailAddress.requestFocus();
            updateLoader(false);
            return;
        }else if (!CommonUtils.isValidMail(emailId)) {
            txtInputEmailAddress.setError(getResources().getString(R.string.email_address_validate_error));
            txtInputEmailAddress.requestFocus();
            updateLoader(false);
            return;
        }else if(TextUtils.isEmpty(password)){
            txtInputPassword.setError(getResources().getString(R.string.password_error));
            txtInputPassword.requestFocus();
            updateLoader(false);
            return;
        }else{

            updateLoader(false);

            int userId = 0;
            userId++;

            UserModel userModel = new UserModel();


            userModel.setUserId(String.valueOf(userId));
            userModel.setUserName(userName);
            userModel.setUserPassword(password);

            preference.createLoginSession(userModel);

            gotoMainActivity();
        }

    }

    private  void resetError(){
        txtInputUserName.setError(null);
        txtInputMobileNumber.setError(null);
        txtInputEmailAddress.setError(null);
        txtInputPassword.setError(null);
    }
    private  void updateLoader(boolean b){

        if(progressBarSignUp == null) return;

        if(b){
            btnSignUp.setVisibility(View.GONE);
            progressBarSignUp.setVisibility(View.VISIBLE);
        }else{
            progressBarSignUp.setVisibility(View.GONE);
            btnSignUp.setVisibility(View.VISIBLE);
        }

    }

    private void gotoMainActivity() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.btnSignUp, R.id.linearLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                gotoSignUp();
                break;
            case R.id.linearLogin:
                gotoLoginActivity();
                break;
        }

    }

}
