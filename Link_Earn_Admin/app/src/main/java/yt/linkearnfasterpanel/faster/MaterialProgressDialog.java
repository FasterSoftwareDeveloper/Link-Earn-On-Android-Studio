package yt.linkearnfasterpanel.faster;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.progressindicator.CircularProgressIndicator;

class MaterialProgressDialog {

    private final Dialog dialog;
    private final CircularProgressIndicator progressIndicator;
    private final TextView messageText;

    public MaterialProgressDialog(Context context) {
    dialog = new Dialog(context);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(LayoutInflater.from(context).inflate(R.layout.material_progress_dialog, null));
    dialog.setCancelable(false);

    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    dialog.getWindow().getAttributes().windowAnimations = R.style.MaterialDialogAnimation;

    progressIndicator = dialog.findViewById(R.id.progress_indicator);
    messageText = dialog.findViewById(R.id.progress_message);

    progressIndicator.setIndicatorColor(
        ContextCompat.getColor(context, R.color.gradient_start),
        ContextCompat.getColor(context, R.color.gradient_middle),
        ContextCompat.getColor(context, R.color.gradient_end)
    );
}

    public void show(String message) {
        if (message != null) {
            messageText.setText(message);
            messageText.setVisibility(View.VISIBLE);
        } else {
            messageText.setVisibility(View.GONE);
        }
        dialog.show();
    }

    public void dismiss() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }
}
