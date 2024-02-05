package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.services;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.models.ClienteModel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SMSService {

    @Value("${TWILIO_ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${TWILIO_AUTH_TOKEN}")
    private String AUTH_TOKEN;

    @Value("${TWILIO_PHONE_NUMBER}")
    private String FROM_NUMBER;

    public void send(ClienteModel sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(sms.getClientNumber()),
                new PhoneNumber(FROM_NUMBER), "Ocorreu uma transação").create();

    }
}
