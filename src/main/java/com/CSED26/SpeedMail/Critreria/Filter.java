package com.csed26.speedmail.critreria;

import com.csed26.speedmail.mail.Mail;
import java.util.*;

public interface Filter {

    public List<Mail> apply(List<Mail> mails);

}
