package io.skygear.skygear_starter_project;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.skygear.skygear.AuthResponseHandler;
import io.skygear.skygear.Configuration;
import io.skygear.skygear.Container;
import io.skygear.skygear.Error;
import io.skygear.skygear.LogoutResponseHandler;
import io.skygear.skygear.Record;

public class MainActivity extends AppCompatActivity {

    private TextView endpointTextView;
    private TextView apiKeyTextView;
    private TextView accessTokenTextView;
    private TextView userIdTextView;

    private EditText emailEditText;
    private EditText passwordEditText;

    private Button signupButton;
    private Button loginButton;
    private Button logoutButton;

    private Container skygear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.endpointTextView = findViewById(R.id.endpoint_text_view);
        this.apiKeyTextView = findViewById(R.id.api_key_text_view);
        this.accessTokenTextView = findViewById(R.id.access_token_text_view);
        this.userIdTextView = findViewById(R.id.user_id_text_view);

        this.emailEditText = findViewById(R.id.email_edit_text);
        this.passwordEditText = findViewById(R.id.password_edit_text);

        this.signupButton = findViewById(R.id.signup_button);
        this.loginButton = findViewById(R.id.login_button);
        this.logoutButton = findViewById(R.id.logout_button);

        this.skygear = Container.defaultContainer(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.updateViewState();
    }

    private void updateViewState() {
        Configuration config = this.skygear.getConfig();
        Record currentUser = this.skygear.getAuth().getCurrentUser();
        String accessToken = this.skygear.getAuth().getCurrentAccessToken();

        this.endpointTextView.setText(String.format("Endpoint: %s", config.getEndpoint()));
        this.apiKeyTextView.setText(String.format("API Key: %s", config.getApiKey()));

        String userId = "Undefined";
        if (currentUser != null) {
            userId = currentUser.getId();
        }

        this.accessTokenTextView.setText(String.format("Access Token: %s", accessToken));
        this.userIdTextView.setText(String.format("User ID: %s", userId));

        this.signupButton.setEnabled(currentUser == null);
        this.loginButton.setEnabled(currentUser == null);
        this.logoutButton.setEnabled(currentUser != null);
    }

    public void signupOnClickHandler(View view) {
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setTitle("Loading");
        loading.setMessage("Signing up...");

        final AlertDialog successDialog = new AlertDialog.Builder(this)
                .setTitle("Sign up success")
                .setMessage("")
                .setNegativeButton("Dismiss", null)
                .create();

        final AlertDialog failDialog = new AlertDialog.Builder(this)
                .setTitle("Sign up failed")
                .setMessage("")
                .setNegativeButton("Dismiss", null)
                .create();

        loading.show();
        this.skygear.getAuth().signupWithEmail(
                this.emailEditText.getText().toString(),
                this.passwordEditText.getText().toString(),
                new AuthResponseHandler() {
                    @Override
                    public void onAuthSuccess(Record user) {
                        successDialog.setMessage(String.format(
                                "Sign up with user ID:\n%s",
                                user.getId()
                        ));

                        loading.dismiss();
                        successDialog.show();

                        MainActivity.this.updateViewState();
                    }

                    @Override
                    public void onAuthFail(Error error) {
                        failDialog.setMessage(String.format("Reason:\n%s", error.getMessage()));

                        loading.dismiss();
                        failDialog.show();
                    }
                }
        );
    }

    public void loginOnClickHandler(View view) {
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setTitle("Loading");
        loading.setMessage("Logging In...");

        final AlertDialog successDialog = new AlertDialog.Builder(this)
                .setTitle("Log in success")
                .setMessage("")
                .setNegativeButton("Dismiss", null)
                .create();

        final AlertDialog failDialog = new AlertDialog.Builder(this)
                .setTitle("Log in failed")
                .setMessage("")
                .setNegativeButton("Dismiss", null)
                .create();

        loading.show();
        this.skygear.getAuth().loginWithEmail(
                this.emailEditText.getText().toString(),
                this.passwordEditText.getText().toString(),
                new AuthResponseHandler() {
                    @Override
                    public void onAuthSuccess(Record user) {
                        successDialog.setMessage(String.format(
                                "Log in with user ID:\n%s",
                                user.getId()
                        ));

                        loading.dismiss();
                        successDialog.show();

                        MainActivity.this.updateViewState();
                    }

                    @Override
                    public void onAuthFail(Error error) {
                        failDialog.setMessage(String.format("Reason:\n%s", error.getMessage()));

                        loading.dismiss();
                        failDialog.show();
                    }
                }
        );
    }

    public void logoutOnClickHandler(View view) {
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setTitle("Loading");
        loading.setMessage("Logging out...");

        final AlertDialog successDialog = new AlertDialog.Builder(this)
                .setTitle("Logout success")
                .setMessage("You have logged out.")
                .setNegativeButton("Dismiss", null)
                .create();

        final AlertDialog failDialog = new AlertDialog.Builder(this)
                .setTitle("Logout failed")
                .setMessage("")
                .setNegativeButton("Dismiss", null)
                .create();

        loading.show();
        this.skygear.getAuth().logout(new LogoutResponseHandler() {
            @Override
            public void onLogoutSuccess() {
                loading.dismiss();
                successDialog.show();

                MainActivity.this.updateViewState();
            }

            @Override
            public void onLogoutFail(Error error) {
                failDialog.setMessage(String.format("Reason:\n%s", error.getMessage()));

                loading.dismiss();
                failDialog.show();
            }
        });
    }
}
