package com.example.ballbask.model;

import java.util.ArrayList;

public class TelaExibir {
    public ArrayList<Team> timesEquipes;
    public String message_Error;

    public boolean TemErro(){
        return message_Error != null;
    }
}
