package com.example.fbdownloader.models;

import java.util.ArrayList;

public class MojVideo {
    public boolean error;
    public String msg;
    public ArrayList<Data> data;

    public class Data {
        public String url;
        public String format;
        public String ext;
        public String format_id;
    }
}
