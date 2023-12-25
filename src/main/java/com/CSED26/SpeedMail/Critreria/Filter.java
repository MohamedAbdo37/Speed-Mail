package com.CSED26.SpeedMail.Critreria;

import com.CSED26.SpeedMail.Mail.Mail;
import java.util.*;

public interface Filter {

    public List<Mail> apply(List<Mail> mails);

}
