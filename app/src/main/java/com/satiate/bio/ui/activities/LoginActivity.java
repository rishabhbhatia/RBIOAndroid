package com.satiate.bio.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.satiate.bio.BattleApplication;
import com.satiate.bio.R;
import com.satiate.bio.network.RetrofitNetworkCalls;
import com.satiate.bio.utils.Const;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rishabh Bhatia on 10/1/2016.
 */

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final int GOOGLE_SIGN_IN = 101;
    @BindView(R.id.ll_login_label_holder)
    LinearLayout llLoginLabelHolder;
    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.ll_login_username_holder)
    LinearLayout llLoginUsernameHolder;
    @BindView(R.id.et_login_pass)
    EditText etLoginPass;
    @BindView(R.id.ll_login_pass_holder)
    LinearLayout llLoginPassHolder;
    @BindView(R.id.ll_login_input_holder)
    LinearLayout llLoginInputHolder;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_forgot_pass)
    TextView tvForgotPass;
    @BindView(R.id.sign_in_button)
    SignInButton signInButton;
    @BindView(R.id.ll_login_buttons_holder)
    LinearLayout llLoginButtonsHolder;
    @BindView(R.id.frame_fragments_container)
    FrameLayout frameFragmentsContainer;
    @BindView(R.id.prl_login_main_holder)
    PercentRelativeLayout prlLoginMainHolder;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        measureLoginButton();
        makeNodeServerRequest();
        setupGoogleSignIn();
    }

    private void measureLoginButton()
    {
        llLoginUsernameHolder.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        etLoginUsername.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        llLoginPassHolder.setMinimumWidth(llLoginUsernameHolder.getMeasuredWidth());
        llLoginPassHolder.setMinimumHeight(llLoginUsernameHolder.getMeasuredHeight());

        etLoginPass.setWidth(etLoginUsername.getMeasuredWidth());
        etLoginPass.setHeight(etLoginUsername.getMeasuredHeight());

        btLogin.setWidth(llLoginUsernameHolder.getMeasuredWidth());
        btLogin.setHeight(llLoginUsernameHolder.getMeasuredHeight());

    }

    private void makeNodeServerRequest() {
        RetrofitNetworkCalls.makeRetrofitCall(LoginActivity.this,
                ((BattleApplication) getApplication()).battleNetworkCalls.getTaskList(),
                "hello");
    }

    private void setupGoogleSignIn() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customize sign-in button. The sign-in button can be displayed in
        // multiple sizes and color schemes. It can also be contextually
        // rendered based on the requested scopes. For example. a red button may
        // be displayed when Google+ scopes are requested, but a white button
        // may be displayed when only basic profile is requested. Try adding the
        // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
        // difference.
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());

        signInButton.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(Const.TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.d(Const.TAG, acct.getDisplayName());
            Log.d(Const.TAG, acct.getEmail());
            Log.d(Const.TAG, acct.getGivenName());
            Log.d(Const.TAG, acct.getPhotoUrl().toString());
        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }
}
