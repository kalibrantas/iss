package iss.controller.rest;

import iss.controller.AbstractISSController;
import iss.message.QuestionMessage;
import iss.message.QuestionnaireAssetMessage;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionnaireServiceController extends AbstractISSController {

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
	public QuestionnaireAssetMessage save(
			@RequestBody ArrayList<QuestionMessage> question) {
		QuestionnaireAssetMessage qa = new QuestionnaireAssetMessage();
		qa.setQuestionList(question);
		qd.saveQuestionnaireAsset(qa);
		return qd.getQuestionnaireAsset(1, 2);
	}

	@RequestMapping(value = "/get"/*
								 * , method = RequestMethod.POST,
								 * consumes="application/json"
								 */)
	public QuestionnaireAssetMessage get() {
		return qd.getQuestionnaireAsset(1, 2);
	}
}
