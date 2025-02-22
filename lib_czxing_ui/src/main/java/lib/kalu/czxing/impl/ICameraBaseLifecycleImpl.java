package lib.kalu.czxing.impl;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.view.PreviewView;
import androidx.fragment.app.FragmentActivity;

/**
 * @description: 相机生命周期Impl
 * @date: 2021-05-17 13:46
 */
interface ICameraBaseLifecycleImpl {

    void start(@NonNull FragmentActivity activity, @NonNull PreviewView previewView, @Nullable ImageView imageView);

    void release(@NonNull Context context);

    void pause(@NonNull FragmentActivity activity);

    void resume(@NonNull FragmentActivity activity);

    @Nullable
    Camera getCamera();
}
