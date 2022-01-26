package com.beta.replyservice.controller;

import com.beta.replyservice.Util.Utility;
import com.beta.replyservice.common.Constants;
import com.beta.replyservice.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import static com.beta.replyservice.Util.Utility.returnEncryptedString;
import static com.beta.replyservice.Util.Utility.reverseString;
import static com.beta.replyservice.common.Constants.*;

@RestController
public class ReplyController {

    @GetMapping("/reply")
    public ReplyMessage replying() {
        return new ReplyMessage("Message is empty");
    }

    @GetMapping("/reply/{message}")
    public ReplyMessage replying(@PathVariable String message) {
        ResponseEntity.status(HttpStatus.ACCEPTED);
        return new ReplyMessage(message);
    }

    @GetMapping("/v2/reply/{message}")
    public ReplyMessage replyingV2(@PathVariable String message) {
        return new ReplyMessage(manageStringInput(message));
    }

    private static String manageStringInput(String message) throws InvalidInputException {
        String processedString = null;

        if (message != null && !message.isEmpty() && Utility.matchesRegex(message)) {
            String[] requiredMessage = splitStringByHyphen(message);

            if (requiredMessage.length < 2 || requiredMessage[1] == null || requiredMessage[1].isEmpty()) {
                throw new InvalidInputException("Invalid Input");
            }
            switch (requiredMessage[0]) {
                case RULE_11:
                    processedString = manage11StringInput(requiredMessage[1]);
                    break;
                case RULE_12:
                    processedString = manage12StringInput(requiredMessage[1]);
                    break;
                case RULE_21:
                    processedString = manage21StringInput(requiredMessage[1]);
                    break;
                case RULE_22:
                    processedString = manage22StringInput(requiredMessage[1]);
                    break;
                default:
                    throw new InvalidInputException("Invalid Input");
            }
        } else {
            throw new InvalidInputException("Invalid Input");
        }
        return processedString;
    }

    private static String manage11StringInput(String message) {
        return message;
    }

    private static String manage12StringInput(String message) {
        return returnEncryptedString(reverseString(message));
    }

    private static String manage21StringInput(String message) {
        return reverseString(returnEncryptedString(message));
    }

    private static String manage22StringInput(String message) {
        return returnEncryptedString(returnEncryptedString(message));
    }

    public static String[] splitStringByHyphen(String message) {
        String[] splitMessage = message.split("-");
        return splitMessage;
    }

}