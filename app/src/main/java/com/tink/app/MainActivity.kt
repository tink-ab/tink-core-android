package com.tink.app

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tink.core.Tink
import com.tink.service.authentication.user.User
import com.tink.service.handler.ResultHandler
import com.tink.service.network.TinkConfiguration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Tink.init(
            TinkConfiguration(
                Configuration.sampleEnvironment,
                Configuration.sampleOAuthClientId,
                Uri.parse("https://tink.com")
            ), this
        )

        Tink.setUser(
            User.fromAccessToken("") // TODO
        )

        Tink.providerRepository().listProviders(ResultHandler({
            Log.d("MainActivity", "providers: $it")
        }, {}))
    }
}
