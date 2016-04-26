package com.ezeapi.sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.charge.api.ChargeAPIConstants;
import com.charge.api.ChargeApi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EzeNativeSampleActivity extends Activity implements
		OnClickListener {

	private EditText txtRefPin, txtChargeAmount, txtName, txtMobile, txtEmail,
			txtChqNum, txtBankCode, txtBankName, txtBankACNum, txtChqDate;

	private Button btnInit, btnPrep, btnCreateCharge, btnCancelCharge,
			btnUpdateCharge, btnCardPay, btnRemotePay, btnWalletPay,
			btnCnfrmWalletPin, btnCashPay, btnChequePay, btnChargeView,
			btnUpdateCustomer, btnChargesearch, btnSearchTxn, btnVoidTxn,
			btnAttachSign, btnUpdate, btnClose;

	private String strTxnId = null, chargeId = null, emiID = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nativesample);

		btnCreateCharge = ((Button) findViewById(R.id.create_charge));
		btnCreateCharge.setOnClickListener(this);

		btnCancelCharge = ((Button) findViewById(R.id.cancel_charge));
		btnCancelCharge.setOnClickListener(this);

		btnUpdateCharge = ((Button) findViewById(R.id.update_charge));
		btnUpdateCharge.setOnClickListener(this);

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
		case R.id.create_charge:
			createCharge();
			break;
		case R.id.cancel_charge:
			cancelCharge();
			break;
		case R.id.update_charge:
			updateCharge();
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

	/**
	 * Method to create a charge
	 * **/
	private void createCharge() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject chargeObject = new JSONObject();

			// Building Customer Address object(Non-Mandatory)
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object(Non-Mandatory)
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString().trim());
			customerObject.put("emailId", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object(Non-Mandatory)
			itemOneObject.put("amount", txtChargeAmount.getText().toString().trim());
			itemOneObject.put("name", "Item name");
			itemOneObject.put("description", "Item Description");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object

			//Mandatory params
			chargeObject.put("amount", txtChargeAmount.getText().toString().trim());
			chargeObject.put("dateTime", "04-04-2016 12:45:00");
			
			//Non-Mandatory params
			chargeObject.put("referenceType", "REF TYPE");
			chargeObject.put("referenceId", "123");
			chargeObject.put("description", "DESCRIPTION");
			
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);

			request.put("charge", chargeObject);
			ChargeApi.createCharge(this,ChargeAPIConstants.REQ_CODE_CREATE_CHARGE,request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Method to update existing charge
	 * **/
	private void updateCharge() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject chargeObject = new JSONObject();

			// Building Customer Address object(Non-Mandatory)
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object(Non-Mandatory)
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString().trim());
			customerObject.put("emailId", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object(Non-Mandatory)
			itemOneObject.put("amount", txtChargeAmount.getText().toString().trim());
			itemOneObject.put("name", "Item name");
			itemOneObject.put("description", "Item Description");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object(Non-Mandatory)
			chargeObject.put("referenceType", "REF TYPE");
			chargeObject.put("referenceId", "123");
			chargeObject.put("description", "DESCRIPTION");
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);
			
			//Mandatory params
			chargeObject.put("amount", txtChargeAmount.getText().toString().trim());
			chargeObject.put("dateTime", "04-04-2016 12:45:00");//Time stamp
			
			request.put("charge", chargeObject);
			request.put("chargeId", chargeId);
			ChargeApi.updateCharge(this,ChargeAPIConstants.REQ_CODE_UPDATE_CHARGE, request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to cancel the existing charge
	 * **/
	private void cancelCharge() {
		ChargeApi.cancelCharge(this, ChargeAPIConstants.REQ_CODE_CANCEL_CHARGE,
				chargeId);
	}


	/**
	 * Method to take card payment
	 * **/
	private void cardPayment() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject chargeObject = new JSONObject();

			// Building Customer Address object(Non-Mandatory)
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object(Non-Mandatory)
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString().trim());
			customerObject.put("emailId", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object(Non-Mandatory)
			itemOneObject.put("amount", txtChargeAmount.getText().toString().trim());
			itemOneObject.put("name", "Item name");
			itemOneObject.put("description", "Item Description");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object
			chargeObject.put("referenceType", "REF TYPE");
			chargeObject.put("referenceId", "123");
			chargeObject.put("amount", txtChargeAmount.getText().toString().trim());
			chargeObject.put("description", "DESCRIPTION");
			chargeObject.put("dateTime", "04-04-2016 12:45:00");
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);

			request.put("charge", chargeObject);
			
			//Pass chargeObject as null if you wish to take a card payment for a given charge ID
			//ChargeApi.cardTransaction(this,ChargeAPIConstants.REQ_CODE_CARD_PAYMENT, chargeId,null);
			
			//Pass chargeId as null if you wish to create charge & take card payment
			ChargeApi.cardTransaction(this,ChargeAPIConstants.REQ_CODE_CARD_PAYMENT, null,request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to initiate a remote transaction
	 * **/
	private void remotePay() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject chargeObject = new JSONObject();

			// Building Customer Address object(Non-Mandatory)
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object(Non-Mandatory)
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString().trim());
			customerObject.put("emailId", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object(Non-Mandatory)
			itemOneObject.put("amount", txtChargeAmount.getText().toString().trim());
			itemOneObject.put("name", "Item name");
			itemOneObject.put("description", "Item Description");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object
			chargeObject.put("referenceType", "REF TYPE");
			chargeObject.put("referenceId", "123");
			chargeObject.put("description", "DESCRIPTION");
			
			//Mandatory params
			chargeObject.put("amount", txtChargeAmount.getText().toString().trim());
			chargeObject.put("dateTime", "04-04-2016 12:45:00");
			
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);

			request.put("charge", chargeObject);

			//Pass chargeObject as null if you wish to make a remote txn for a given charge ID
			//ChargeApi.remoteTransaction(this,ChargeAPIConstants.REQ_CODE_REMOTE_PAYMENT, chargeId, null);
			
			//Pass chargeId as null if you wish to to create charge & make a remote txn
			ChargeApi.remoteTransaction(this,ChargeAPIConstants.REQ_CODE_REMOTE_PAYMENT,null,request.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to Initiate a Wallet Transaction
	 * **/
	private void walletPayInitiate() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject chargeObject = new JSONObject();
			JSONObject walletObject = new JSONObject();
			JSONObject wallet = new JSONObject();

			// Building Customer Address object(Non-Mandatory)
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object(Non-Mandatory)
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString().trim());
			customerObject.put("emailId", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object(Non-Mandatory)
			itemOneObject.put("amount", txtChargeAmount.getText().toString().trim());
			itemOneObject.put("name", "Item name");
			itemOneObject.put("description", "Item Description");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object(Non-Mandatory)
			chargeObject.put("referenceType", "REF TYPE");
			chargeObject.put("referenceId", "123");
			chargeObject.put("description", "DESCRIPTION");
			
			//Mandatory params
			chargeObject.put("amount", txtChargeAmount.getText().toString().trim());
			chargeObject.put("dateTime", "04-04-2016 12:45:00");
			
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);
			request.put("charge", chargeObject);

			//Pass the Ezetap configured wallet provider name here
			walletObject.put("walletProvider", "ezetap");
			request.put("wallet", walletObject);
			wallet.put("wallet", walletObject);

			//Pass chargeObject as null if you wish to make a wallet txn for a given charge ID
			//ChargeApi.walletTransaction(this,ChargeAPIConstants.REQ_CODE_WALLET_PAYMENT, chargeId, null,wallet.toString());
			
			//Pass chargeId as null if you wish to to create charge & make a wallet txn
			ChargeApi.walletTransaction(this,ChargeAPIConstants.REQ_CODE_WALLET_PAYMENT,null,request.toString(),wallet.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to enter One-Time-Password for a initiated wallet txn
	 * **/
	private void confirmWalletPay() {
		try {
			JSONObject request = new JSONObject();
			// Building request Object
			request.put("txnId", strTxnId);//Transaction ID
			request.put("walletOTP", txtRefPin.getText().toString().trim());//OTP
			ChargeApi.confirmPin(this,ChargeAPIConstants.REQ_CODE_CONFIRM_WALLET_PAYMENT,request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to make a cash transaction
	 * **/
	private void cashPayment() {
		try {
			JSONObject request = new JSONObject();
			JSONObject chargeObject = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject itemTwoObject = new JSONObject();

			// Building Customer Address object(Non-Mandatory)
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object(Non-Mandatory)
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString().trim());
			customerObject.put("emailId", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object(Non-Mandatory)
			itemOneObject.put("amount", txtChargeAmount.getText().toString().trim());
			itemOneObject.put("name", "Item name");
			itemOneObject.put("description", "Item Description");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);
			itemTwoObject.put("amount", 0);
			itemTwoObject.put("name", "Special Meal");
			itemTwoObject.put("description", "Meal Type-1");
			itemTwoObject.put("quantity", "1");
			itemTwoObject.put("productId", "pr_1234");
			jsonArray.put(1, itemTwoObject);

			// Building request Object
			chargeObject.put("referenceType", "REF TYPE");
			chargeObject.put("referenceId", "123");
			chargeObject.put("description", "DESCRIPTION");
			
			//Mandatory params
			chargeObject.put("amount", txtChargeAmount.getText().toString().trim());
			chargeObject.put("dateTime", "03-04-2016 12:45:00");
			
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);

			request.put("charge", chargeObject);
			
			//Pass chargeObject as null if you wish to make a cash txn for a given charge ID
			//ChargeApi.cashTransaction(this,ChargeAPIConstants.REQ_CODE_CASH_PAYMENT, chargeId, null);
			
			//Pass chargeId as null if you wish to to create charge & make a cash txn
			ChargeApi.cashTransaction(this,ChargeAPIConstants.REQ_CODE_CASH_PAYMENT,null,request.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to make a cheque transaction
	 * **/
	private void chequePayment() {
		try {
			JSONObject request = new JSONObject();
			JSONObject addressObject = new JSONObject();
			JSONObject customerObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject itemOneObject = new JSONObject();
			JSONObject jsonCheque = new JSONObject();
			JSONObject chargeObject = new JSONObject();

			// Building Customer Address object(Non-Mandatory)
			addressObject.put("street1", "street1");
			addressObject.put("street2", "street2");
			addressObject.put("city", "city");
			addressObject.put("state", "state");
			addressObject.put("postalCode", "postalCode");
			addressObject.put("landmark", "landmark");

			// Building Customer Object(Non-Mandatory)
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString().trim());
			customerObject.put("emailId", txtEmail.getText().toString().trim());
			customerObject.put("address", addressObject);

			// Building items Object(Non-Mandatory)
			itemOneObject.put("amount", txtChargeAmount.getText().toString().trim());
			itemOneObject.put("name", "Item name");
			itemOneObject.put("description", "Item Description");
			itemOneObject.put("quantity", "1");
			itemOneObject.put("productId", "pr_1234");
			jsonArray.put(0, itemOneObject);

			// Building request Object
			chargeObject.put("referenceType", "REF TYPE");
			chargeObject.put("referenceId", "123");
			chargeObject.put("description", "DESCRIPTION");
			
			//Mandatory params
			chargeObject.put("amount", txtChargeAmount.getText().toString().trim());
			chargeObject.put("dateTime", "04-04-2016 12:45:00");
			
			// Building Cheque Object(Mandatory)
			jsonCheque.put("chequeNumber", txtChqNum.getText().toString().trim());
			jsonCheque.put("bankCode", txtBankCode.getText().toString().trim());
			jsonCheque.put("bankName", txtBankName.getText().toString().trim());
			jsonCheque.put("bankAccountNo", txtBankACNum.getText().toString().trim());
			jsonCheque.put("chequeDate", txtChqDate.getText().toString().trim());
			
			chargeObject.put("customer", customerObject);
			chargeObject.put("item", jsonArray);

			request.put("charge", chargeObject);
			request.put("cheque", jsonCheque);

			JSONObject chequeObject = new JSONObject();
			chequeObject.put("cheque", jsonCheque);

			//Pass chargeObject as null if you wish to make a cheque txn for a given charge ID
			//ChargeApi.chequeTransaction(this,ChargeAPIConstants.REQ_CODE_CHEQUE_PAYMENT, chargeId, null,chequeObject.toString());
			
			//Pass chargeId as null if you wish to to create charge & make a cheque txn
			ChargeApi.chequeTransaction(this,ChargeAPIConstants.REQ_CODE_CHEQUE_PAYMENT,null,request.toString(),chequeObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to search charge
	 * **/
	private void searchCharge() {
		JSONObject request = new JSONObject();
		try {
			request.put("status", "PAID_IN_FULL");
			request.put("date", "03-04-2016");// date --- "dd-MM-yyyy"
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ChargeApi.searchCharges(this,ChargeAPIConstants.REQ_CODE_SEARCH_CHARGE, request.toString());
	}

	/**
	 * Method to view charge
	 * **/
	private void viewCharge() {
		ChargeApi.viewCharge(this, ChargeAPIConstants.REQ_CODE_VIEW_CHARGE,
				chargeId);
	}

	/**
	 * Method to add/edit customer for an existing charge
	 * **/
	private void updateCustomer() {
		try {
			JSONObject requestObject = new JSONObject();
			JSONObject customerObject = new JSONObject();

			// Building customer object
			customerObject.put("name", txtName.getText().toString().trim());
			customerObject.put("mobileNo", txtMobile.getText().toString().trim());
			customerObject.put("emailId", txtEmail.getText().toString().trim());

			// Building server request object
			requestObject.put("chargeId", chargeId);
			requestObject.put("customer", customerObject);

			ChargeApi.updateCustomer(this,ChargeAPIConstants.REQ_CODE_UPDATE_CUSTOMER,requestObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Method to initialize Ezetap API's
	 * This method has to be called before making any operation
	 **/
	private void initEzetap() {
		JSONObject jsonRequest = new JSONObject();
		try {
			jsonRequest.put("demoAppKey","Demo app key here.");
			jsonRequest.put("prodAppKey","Prod app key here.");
			jsonRequest.put("merchantName", "Merchant name here");
			jsonRequest.put("userName", "user name here");
			jsonRequest.put("currencyCode", "INR");
			jsonRequest.put("appMode", "DEMO");
			jsonRequest.put("captureSignature", "true");
			jsonRequest.put("accountId", "Your charge account ID here.");
			// Set 'prepareDevice' to true if you want to prepare device while initializing
			jsonRequest.put("prepareDevice", "false");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ChargeApi.initialize(this, ChargeAPIConstants.REQ_CODE_INITIALIZE,jsonRequest);
	}

	/**
	 * Method to Prepare Ezetap's card payment device
	 **/
	private void prepareEzetap() {
		ChargeApi.prepareDevice(this, ChargeAPIConstants.REQ_CODE_PREPARE);
	}

	/**
	 * Method to get the transaction detail
	 **/
	private void getTransaction() {
		ChargeApi.viewTransaction(this,ChargeAPIConstants.REQ_CODE_VIEW_TRANSACTION, strTxnId);
	}

	/**
	 * Method to void a transaction
	 **/
	private void voidTxn() {
		if (isTransactionIdValid()) {
			ChargeApi.voidTransaction(this, ChargeAPIConstants.REQ_CODE_VOID,strTxnId);
		} else
			displayToast("Inorrect txn Id, please make a Txn.");
	}

	/**
	 * Method to attach signature
	 **/
	private void attachSignature() {
		if (isTransactionIdValid()) {
			JSONObject jsonRequest = new JSONObject();
			JSONObject jsonImageObj = new JSONObject();
			try {
				// Building Image Object
				jsonImageObj.put("imageData","The Base64 Image bitmap string of your siganture goes here");
				jsonImageObj.put("imageType", "JPEG");
				jsonImageObj.put("height", "");// optional
				jsonImageObj.put("weight", "");// optional

				// Building final request object
				jsonRequest.put("emiId", emiID);// pass this field if you have an email Id associated with the transaction
				jsonRequest.put("tipAmount", 0.00);// optional
				// jsonRequest.put("image", jsonImageObj); // Pass this
				// attribute when you have a valid captured signature
				jsonRequest.put("txnId", strTxnId);

				ChargeApi.attachSignature(this,ChargeAPIConstants.REQ_CODE_ATTACHSIGN, jsonRequest);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else
			displayToast("Inorrect txn Id, please make a Txn.");
	}

	/**
	 * Method to check for Ezetap's firmware updates
	 **/
	private void updateDevice() {
		ChargeApi.updateDevice(this, ChargeAPIConstants.REQ_CODE_UPDATE);
	}

	/**
	 * Method to logout from Ezetap SDK.
	 **/
	private void closeEzetap() {
		ChargeApi.close(this, ChargeAPIConstants.REQ_CODE_CLOSE);
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
			Log.d("Message", intent.getStringExtra("response") + " ");
		showDialog(intent.getStringExtra("response"));
		switch (requestCode) {
		case ChargeAPIConstants.REQ_CODE_CASH_PAYMENT:
		case ChargeAPIConstants.REQ_CODE_CARD_PAYMENT:
		case ChargeAPIConstants.REQ_CODE_CHEQUE_PAYMENT:
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
		case ChargeAPIConstants.REQ_CODE_CREATE_CHARGE:
			if (resultCode == RESULT_OK) {
				try {
					JSONObject response = new JSONObject(
							intent.getStringExtra("response"));
					response = response.getJSONObject("result");
					chargeId = response.get("chargeId").toString();
					Log.d("Message", chargeId + " ");
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
				try {
					JSONObject response = new JSONObject(
							intent.getStringExtra("response"));
					response = response.getJSONObject("result");
					JSONObject charge = response.getJSONObject("charge");
					chargeId = charge.getString("chargeId");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		case ChargeAPIConstants.REQ_CODE_UPDATE:
		default:
			break;
		}

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	public void showDialog(String msg) {
		Dialog dialog = new Dialog(EzeNativeSampleActivity.this);
		dialog.setContentView(R.layout.dialog_box);
		dialog.setTitle("Response");
		TextView textview = (TextView) dialog.findViewById(R.id.textmsg);
		textview.setText(msg);
		dialog.show();
	}

}
