package com.example.animationrotate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Animation mFadeInAnimation, mFadeOutAnimation, mFadeOutMiddleAnimation, mFadeInMiddleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.myImage);
    }

    public void btnRight(View view) {
        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        imageView.startAnimation(rotate);
    }

    public void btnLeft(View view) {
        Animation _rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim._rotate);
        imageView.startAnimation(_rotate);
    }

    public void btnVisability(View view) {
        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_in);
        mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_out);
        mFadeInAnimation.setAnimationListener(animationIn);
        mFadeOutAnimation.setAnimationListener(animationOut);

        imageView.startAnimation(mFadeOutAnimation);
    }

    public void btnMove(View view) {
        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.move_down_to_up);
        mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.move_left_to_right);
        mFadeOutMiddleAnimation = AnimationUtils.loadAnimation(this, R.anim.move_right_to_left);
        mFadeInMiddleAnimation = AnimationUtils.loadAnimation(this, R.anim.move_up_to_down);

        mFadeInAnimation.setAnimationListener(animationFadeInListener);
        mFadeOutAnimation.setAnimationListener(animationFadeOutListener);
        mFadeInMiddleAnimation.setAnimationListener(animationInFadeMiddleListener);
        mFadeOutMiddleAnimation.setAnimationListener(animationOutFadeMiddleListener);

        imageView.startAnimation(mFadeOutAnimation);
    }

    Animation.AnimationListener animationFadeOutListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {    }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.startAnimation(mFadeOutMiddleAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {    }
    };

    Animation.AnimationListener animationInFadeMiddleListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {    }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.startAnimation(mFadeInAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {    }
    };

    Animation.AnimationListener animationOutFadeMiddleListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {    }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.startAnimation(mFadeInMiddleAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {    }
    };

    Animation.AnimationListener animationFadeInListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {    }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.startAnimation(mFadeOutAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {    }
    };

    Animation.AnimationListener animationOut = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {    }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.startAnimation(mFadeInAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {    }
    };

    Animation.AnimationListener animationIn = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {    }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.startAnimation(mFadeOutAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {    }
    };
}