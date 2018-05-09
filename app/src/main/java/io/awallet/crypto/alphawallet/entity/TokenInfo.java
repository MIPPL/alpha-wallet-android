package io.awallet.crypto.alphawallet.entity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import io.awallet.crypto.alphawallet.R;
import io.awallet.crypto.alphawallet.ui.AddTokenActivity;
import io.awallet.crypto.alphawallet.ui.widget.holder.TokenHolder;
import io.awallet.crypto.alphawallet.viewmodel.BaseViewModel;
import io.awallet.crypto.alphawallet.viewmodel.TokensViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static io.awallet.crypto.alphawallet.ui.widget.holder.TokenHolder.EMPTY_BALANCE;

public class TokenInfo implements Parcelable {
    public final String address;
    public final String name;
    public final String symbol;
    public final int decimals;
    public boolean isEnabled;
    public final boolean isStormbird;

    public TokenInfo(String address, String name, String symbol, int decimals, boolean isEnabled) {
        this.address = address;
        this.name = name;
        this.symbol = symbol;
        this.decimals = decimals;
        this.isEnabled = isEnabled;
        this.isStormbird = false;
    }
    public TokenInfo(String address, String name, String symbol, int decimals, boolean isEnabled, boolean isStormbird) {
        this.address = address;
        this.name = name;
        this.symbol = symbol;
        this.decimals = decimals;
        this.isEnabled = isEnabled;
        this.isStormbird = isStormbird;
    }

    public TokenInfo(Parcel in) {
        address = in.readString();
        name = in.readString();
        symbol = in.readString();
        decimals = in.readInt();
        isEnabled = in.readInt() == 1;
        isStormbird = in.readInt() == 1;
    }

    public static final Creator<TokenInfo> CREATOR = new Creator<TokenInfo>() {
        @Override
        public TokenInfo createFromParcel(Parcel in) {
            return new TokenInfo(in);
        }

        @Override
        public TokenInfo[] newArray(int size) {
            return new TokenInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(name);
        dest.writeString(symbol);
        dest.writeInt(decimals);
        dest.writeInt(isEnabled ? 1 : 0);
        dest.writeInt(isStormbird ? 1 : 0);
    }

    public void addTokenSetupPage(AddTokenActivity layout) {
        layout.inputAddressView.setAddress(address);
        layout.symbolInputView.setText(symbol);
        layout.decimalsInputView.setText(String.valueOf(decimals));
        layout.nameInputview.setText(name);
        layout.ticketLayout.setVisibility(View.GONE);
        layout.isStormbird = isStormbird;
    }
}