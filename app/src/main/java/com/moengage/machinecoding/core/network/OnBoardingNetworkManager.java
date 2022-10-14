package com.moengage.machinecoding.core.network;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.InputStream;

public final class OnBoardingNetworkManager extends NetworkManager {
    private static final String ONBOARDING_FILENAME = "onboarding.json";

    public static byte[] getOnboardingQuestions(@NonNull Context context) {
        return getJson(context, ONBOARDING_FILENAME);
    }

    @Nullable
    public static InputStream getOnboardingQuestionsStream(@NonNull Context context) {
        return getJsonStream(context, ONBOARDING_FILENAME);
    }
}