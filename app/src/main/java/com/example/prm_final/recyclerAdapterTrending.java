<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">

    <RelativeLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toTopOf="@+id/cardView1"
        app:layout_constraintEnd_toStartOf="@+id/cardView1"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

    </RelativeLayout>

    <androidx.cardview.widget.CardView
        android:id="@+id/cardView1"
        android:layout_width="280dp"
        android:layout_height="400dp"
        android:layout_marginStart="30dp"
        android:layout_marginEnd="10dp"
        app:cardCornerRadius="20dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <Button
            android:id="@+id/scoreTrending"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="180dp"
            android:layout_marginTop="10dp"
            android:alpha="0.7"
            android:background="@drawable/btn_score"
            android:backgroundTint="@color/black"
            android:drawableLeft="@drawable/ic_baseline_star_24"
            android:paddingLeft="6dip"
            android:text="9.7"
            android:textColor="#fff"
            android:textSize="20sp" />

        <ImageView
            android:id="@+id/filmImageTrending"
            android:layout_width="280dp"
            android:layout_height="400dp"
            android:alpha="0.9"
            android:scaleType="centerCrop"
            android:src="@drawable/amobpsycho100iii"
            app:cardCornerRadius="20dp" />

        <Button
            android:id="@+id/filmNameTreding"
            android:layout_width="250dp"
            android:layout_height="60dp"
            android:layout_marginLeft="15dp"
            android:layout_marginTop="310dp"
            android:alpha="0.7"
            android:background="@drawable/btn_score"
            android:backgroundTint="#000000"
            android:text="Dr.Strange 2 " />

        <!--        <TextView-->
        <!--            android:id="@+id/textView4"-->
        <!--            android:layout_width="250dp"-->
        <!--            android:layout_height="62dp"-->
        <!--            android:layout_marginLeft="15dp"-->
        <!--            android:layout_marginTop="310dp"-->
        <!--            android:gravity="center|center_vertical"-->
        <!--            android:text="Dr.Strange 2"-->
        <!--            android:textSize="20sp" />-->


    </androidx.cardview.widget.CardView>

</androidx.constraintlayout.widget.ConstraintLayout>
