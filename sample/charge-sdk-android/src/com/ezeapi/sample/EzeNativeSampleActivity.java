package com.ezeapi.sample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.charge.api.ChargeAPIConstants;
import com.charge.api.ChargeApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EzeNativeSampleActivity extends Activity implements
		OnClickListener {

	private EditText txtRefPin, txtChargeAmount, txtName, txtMobile, txtEmail,
			txtChqNum, txtBankCode, txtBankName, txtBankACNum, txtChqDate;

	private Button btnInit, btnPrep, btnCardPay, btnRemotePay, btnWalletPay,
			btnCnfrmWalletPin, btnCashPay, btnChequePay, btnChargeView,
			btnUpdateCustomer, btnChargesearch, btnSearchTxn, btnVoidTxn,
			btnAttachSign, btnUpdate, btnClose;

	private String strTxnId = null, chargeId = null, emiID = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nativesample);

		btnCardPay = ((Button) findViewById(R.id.btnCardPay));
		btnCardPay.setOnClickListener(this);

		btnRemotePay = ((Button) findViewById(R.id.btnRemotePay));
		btnRemotePay.setOnClickListener(this);

		btnWalletPay = ((Button) findViewById(R.id.btnWalletPay));
		btnWalletPay.setOnClickListener(this);

		btnCnfrmWalletPin = ((Button) findViewById(R.id.btnCnfrmWalletPin));
		btnCnfrmWalletPin.setOnClickListener(this);

		btnCashPay = ((Button) findViewById(R.id.btnCashPay));
		btnCashPay.setOnClickListener(this);

		btnChequePay = ((Button) findViewById(R.id.btnChequePay));
		btnChequePay.setOnClickListener(this);

		btnChargeView = ((Button) findViewById(R.id.btnChargeView));
		btnChargeView.setOnClickListener(this);

		btnUpdateCustomer = ((Button) findViewById(R.id.btnUpdateCustomer));
		btnUpdateCustomer.setOnClickListener(this);

		btnChargesearch = ((Button) findViewById(R.id.btnSearchCharge));
		btnChargesearch.setOnClickListener(this);

		btnInit = ((Button) findViewById(R.id.btnInitialize));
		btnInit.setOnClickListener(this);

		btnPrep = ((Button) findViewById(R.id.btnPrepare));
		btnPrep.setOnClickListener(this);

		btnSearchTxn = ((Button) findViewById(R.id.btnSearchTxn));
		btnSearchTxn.setOnClickListener(this);

		btnVoidTxn = ((Button) findViewById(R.id.btnVoidTxn));
		btnVoidTxn.setOnClickListener(this);

		btnAttachSign = ((Button) findViewById(R.id.btnAttachSignature));
		btnAttachSign.setOnClickListener(this);

		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnUpdate.setOnClickListener(this);

		btnClose = ((Button) findViewById(R.id.btnClose));
		btnClose.setOnClickListener(this);

		txtRefPin = (EditText) findViewById(R.id.txtRefPin);
		txtChargeAmount = (EditText) findViewById(R.id.txtChrgAmount);
		txtName = (EditText) findViewById(R.id.txtName);
		txtMobile = (EditText) findViewById(R.id.txtMobile);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtChqNum = (EditText) findViewById(R.id.txtChqNum);
		txtBankCode = (EditText) findViewById(R.id.txtBankCode);
		txtBankName = (EditText) findViewById(R.id.txtBankName);
		txtBankACNum = (EditText) findViewById(R.id.txtAcNum);
		txtChqDate = (EditText) findViewById(R.id.txtChqDate);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnInitialize:
			initEzetap();
			break;
		case R.id.btnPrepare:
			prepareEzetap();
			break;

		case R.id.btnCardPay:
			cardPayment();
			break;
		case R.id.btnRemotePay:
			remotePay();
			break;
		case R.id.btnWalletPay:
			walletPayInitiate();
			break;
		case R.id.btnCnfrmWalletPin:
			confirmWalletPay();
			break;
		case R.id.btnCashPay:
			cashPayment();
			break;
		case R.id.btnChequePay:
			chequePayment();
			break;
		case R.id.btnChargeView:
			viewCharge();
			break;
		case R.id.btnUpdateCustomer:
			updateCustomer();
			break;

		case R.id.btnSearchCharge:
			searchCharge();
			break;
		case R.id.btnSearchTxn:
			getTransaction();
			break;
		case R.id.btnVoidTxn:
			voidTxn();
			break;
		case R.id.btnAttachSignature:
			attachSignature();
			break;
		case R.id.btnUpdate:
			updateDevice();
			break;
		case R.id.btnClose:
			closeEzetap();
			break;

		default:
			break;
		}
	}

	private void cardPayment() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject chargeObject = new JSONObject();

			// Building Customer Address object
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString()
					.trim());
			customerObject.put("email", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object
			itemOneObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			itemOneObject.put("name", "Special Meal");
			itemOneObject.put("description", "Meal Type-1");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object
			chargeObject.put("referenceType", "ORDER-500");
			chargeObject.put("referenceId", "1012");
			chargeObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			chargeObject.put("description", "Table 500");
			chargeObject.put("dateTime", "04-04-2016 12:45:00");
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);

			request.put("charge", chargeObject);

			ChargeApi.cardTransaction(this,
					ChargeAPIConstants.REQ_CODE_CARD_PAYMENT,
					request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void remotePay() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject chargeObject = new JSONObject();

			// Building Customer Address object
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString()
					.trim());
			customerObject.put("email", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object
			itemOneObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			itemOneObject.put("name", "Special Meal");
			itemOneObject.put("description", "Meal Type-1");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object
			chargeObject.put("referenceType", "ORDER-500");
			chargeObject.put("referenceId", "1012");
			chargeObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			chargeObject.put("description", "Table 500");
			chargeObject.put("dateTime", "04-04-2016 12:45:00");
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);

			request.put("charge", chargeObject);

			ChargeApi.remoteTransaction(this,
					ChargeAPIConstants.REQ_CODE_REMOTE_PAYMENT,
					request.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void walletPayInitiate() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject chargeObject = new JSONObject();

			// Building Customer Address object
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString()
					.trim());
			customerObject.put("email", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object
			itemOneObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			itemOneObject.put("name", "Special Meal");
			itemOneObject.put("description", "Meal Type-1");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object
			chargeObject.put("referenceType", "ORDER-500");
			chargeObject.put("referenceId", "1012");
			chargeObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			chargeObject.put("description", "Table 500");
			chargeObject.put("dateTime", "04-04-2016 12:45:00");
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);
			chargeObject.put("walletProvider", "ezetap");

			request.put("charge", chargeObject);

			ChargeApi.walletTransaction(this,
					ChargeAPIConstants.REQ_CODE_WALLET_PAYMENT,
					request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void confirmWalletPay() {
		try {
			JSONObject request = new JSONObject();

			// Building request Object
			request.put("txnId", strTxnId);
			request.put("walletCustomerAuthPin", txtRefPin.getText().toString()
					.trim());

			ChargeApi.confirmPin(this,
					ChargeAPIConstants.REQ_CODE_CONFIRM_WALLET_PAYMENT,
					request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cashPayment() {
		try {
			JSONObject request = new JSONObject();
			JSONObject chargeObject = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();

			// Building Customer Address object
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString()
					.trim());
			customerObject.put("email", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object
			itemOneObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			itemOneObject.put("name", "Special Meal");
			itemOneObject.put("description", "Meal Type-1");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object
			chargeObject.put("referenceType", "ORDER-500");
			chargeObject.put("referenceId", "1012");
			chargeObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			chargeObject.put("description", "Table 500");
			chargeObject.put("dateTime", "03-04-2016 12:45:00");
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);

			request.put("charge", chargeObject);

			ChargeApi.cashTransaction(this,
					ChargeAPIConstants.REQ_CODE_CASH_PAYMENT,
					request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void chequePayment() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject jsonCheque = new JSONObject();
			JSONObject chargeObject = new JSONObject();

			// Building Customer Address object
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString()
					.trim());
			customerObject.put("email", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building Cheque Object
			jsonCheque.put("chequeNumber", txtChqNum.getText().toString()
					.trim());
			jsonCheque.put("bankCode", txtBankCode.getText().toString().trim());
			jsonCheque.put("bankName", txtBankName.getText().toString().trim());
			jsonCheque.put("bankAccountNo", txtBankACNum.getText().toString()
					.trim());
			jsonCheque
					.put("chequeDate", txtChqDate.getText().toString().trim());

			// Building items Object
			itemOneObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			itemOneObject.put("name", "Special Meal");
			itemOneObject.put("description", "Meal Type-1");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object
			chargeObject.put("referenceType", "ORDER-500");
			chargeObject.put("referenceId", "1012");
			chargeObject.put("amount", txtChargeAmount.getText().toString()
					.trim());
			chargeObject.put("description", "Table 500");
			chargeObject.put("dateTime", "04-04-2016 12:45:00");
			chargeObject.put("customer", customerObject);
			chargeObject.put("cheque", jsonCheque);
			chargeObject.put("item", jsonArray);

			request.put("charge", chargeObject);

			ChargeApi.chequeTransaction(this,
					ChargeAPIConstants.REQ_CODE_CHEQUE_PAYMENT,
					request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchCharge() {
		JSONObject request = new JSONObject();
		try {
			request.put("status", "PAID_IN_FULL");
			// date --- "dd-MM-yyyy"
			request.put("date", "03-04-2016");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ChargeApi.searchCharges(this, ChargeAPIConstants.REQ_CODE_SEARCH_CHARGE,
				request.toString());
	}

	private void viewCharge() {
		ChargeApi
				.viewCharge(this, ChargeAPIConstants.REQ_CODE_VIEW_CHARGE, chargeId);
	}

	private void updateCustomer() {
		try {
			JSONObject requestObject = new JSONObject();
			JSONObject customerObject = new JSONObject();

			// Building customer object
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString()
					.trim());
			customerObject.put("emailId", txtEmail.getText().toString().trim());

			// Building server request object
			requestObject.put("chargeId", chargeId);
			requestObject.put("customer", customerObject);

			ChargeApi.updateCustomer(this,
					ChargeAPIConstants.REQ_CODE_UPDATE_CUSTOMER,
					requestObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initEzetap() {
		JSONObject jsonRequest = new JSONObject();
		try {
			jsonRequest.put("demoAppKey",
					"Enter your demo app key");
			jsonRequest.put("prodAppKey",
					"Enter your prod app key");
			jsonRequest.put("merchantName", "Enter your merchant name");
			jsonRequest.put("userName", "Enter your user name");
			jsonRequest.put("currencyCode", "Enter your currency code(eg. INR)");
			jsonRequest.put("appMode", "Enter your app mode(demo/prod)");
			jsonRequest.put("captureSignature", "truefalse");
			jsonRequest.put("prepareDevice", "true/false");// Set it true if you want
														// to initialize and
														// prepare device at a
														// time
														// or false if you want
														// to initialize only
														// and prepare device
														// later for card txn.
			jsonRequest.put("accountId", "Enter your account Id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ChargeApi.initialize(this, ChargeAPIConstants.REQ_CODE_INITIALIZE,
				jsonRequest);
	}

	private void prepareEzetap() {
		ChargeApi.prepareDevice(this, ChargeAPIConstants.REQ_CODE_PREPARE);
	}

	private void getTransaction() {
		ChargeApi.viewTransaction(this,
				ChargeAPIConstants.REQ_CODE_VIEW_TRANSACTION, strTxnId);
		// JSONObject jsonRequest = new JSONObject();
		// try {
		// jsonRequest.put("agentName", "Demo User");
		// jsonRequest.put("startDate", "1/1/2015");
		// jsonRequest.put("endDate", "12/31/2015");
		// jsonRequest.put("txnType", "cash");
		// jsonRequest.put("txnStatus", "void");
		// ChargeApi.searchTransaction(this, REQUESTCODE_SEARCH, jsonRequest);
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
	}

	private void voidTxn() {
		if (isTransactionIdValid()) {
			ChargeApi.voidTransaction(this, ChargeAPIConstants.REQ_CODE_VOID,
					strTxnId);
		} else
			displayToast("Inorrect txn Id, please make a Txn.");
	}

	private void attachSignature() {
		if (isTransactionIdValid()) {
			JSONObject jsonRequest = new JSONObject();
			JSONObject jsonImageObj = new JSONObject();
			try {
				// Building Image Object
				jsonImageObj
						.put("imageData",
								"The Base64 Image bitmap string of your siganture goes here");// Cannot
																								// have
																								// amount
																								// for
																								// CASH@POS
																								// transaction.
				jsonImageObj.put("imageType", "JPEG");
				jsonImageObj.put("height", "");// optional
				jsonImageObj.put("weight", "");// optional

				// Building final request object
				jsonRequest.put("emiId", emiID);// pass this field if you have
												// an
												// email Id associated with the
												// transaction
				jsonRequest.put("tipAmount", 0.00);// optional
				// jsonRequest.put("image", jsonImageObj); // Pass this
				// attribute when you have a valid captured signature
				jsonRequest.put("txnId", strTxnId);

				ChargeApi.attachSignature(this,
						ChargeAPIConstants.REQ_CODE_ATTACHSIGN, jsonRequest);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else
			displayToast("Inorrect txn Id, please make a Txn.");
	}

	private void updateDevice() {
		ChargeApi.updateDevice(this, ChargeAPIConstants.REQ_CODE_UPDATE);
	}

	private void closeEzetap() {
//		ChargeApi.close(this, ChargeAPIConstants.REQ_CODE_CLOSE);
		ChargeApi.close(this, 1111);
	}

	private boolean isTransactionIdValid() {
		if (strTxnId == null)
			return false;
		else
			return true;
	}

	private void displayToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (intent != null && intent.hasExtra("response"))
			Toast.makeText(this, intent.getStringExtra("response"),
					Toast.LENGTH_SHORT).show();
		if (intent != null && intent.hasExtra("response"))
			Log.d("response", intent.getStringExtra("response") + " ");
		switch (requestCode) {
		case ChargeAPIConstants.REQ_CODE_CASH_PAYMENT:
		case ChargeAPIConstants.REQ_CODE_CARD_PAYMENT:
		case ChargeAPIConstants.REQ_CODE_CHEQUE_PAYMENT:
		case ChargeAPIConstants.REQ_CODE_UPDATE:
			if (resultCode == RESULT_OK) {
				try {
					JSONObject response = new JSONObject(
							intent.getStringExtra("response"));
					response = response.getJSONObject("result");
					JSONObject charge = response.getJSONObject("charge");
					chargeId = charge.getString("chargeId");
					response = response.getJSONObject("currentTxn");
					strTxnId = response.getString("txnId");
					emiID = response.getString("emiId");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case ChargeAPIConstants.REQ_CODE_WALLET_PAYMENT:
			try {
				if (resultCode == RESULT_OK) {
					JSONObject jsonObject = new JSONObject(
							intent.getStringExtra("response"));
					jsonObject = new JSONObject(jsonObject.get("result")
							.toString());
					chargeId = jsonObject.getString("chargeId").toString()
							.trim();
					strTxnId = jsonObject.getString("currentTxnId").toString()
							.trim();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		case ChargeAPIConstants.REQ_CODE_CONFIRM_WALLET_PAYMENT:
			if (resultCode == RESULT_OK) {
				try {
					JSONObject response = new JSONObject(
							intent.getStringExtra("response"));
					response = response.getJSONObject("result");
					JSONObject charge = response.getJSONObject("charge");
					chargeId = charge.getString("chargeId");
					response = response.getJSONObject("currentTransaction");
					strTxnId = response.getString("txnId");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case ChargeAPIConstants.REQ_CODE_REMOTE_PAYMENT:
			if (resultCode == RESULT_OK) {
				Log.d("remote pay", intent.getStringExtra("response") + " ");
			}
			break;
		case 1111:
			if (resultCode == RESULT_OK) {
				Log.d("remote pay", intent.getStringExtra("response") + " ");
			}
			break;
		default:
			break;
		}

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
