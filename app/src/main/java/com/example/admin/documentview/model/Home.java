package com.example.admin.etest.model;

import com.example.admin.etest.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Home implements Serializable {
    private String name;

    public Home(String name) {
        this.name = name;
    }

    public Home() {
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        if (name.equals("word")) {
            return R.drawable.docx;
        } else if (name.equals("excel")) {
            return R.drawable.xlxs;
        } else if (name.equals("powerpoint")) {
            return R.drawable.pptx;
        } else if (name.equals("html")) {
            return R.drawable.html;
        } else if (name.equals("text")) {
            return R.drawable.txt;
        } else if (name.equals("pdf")) {
            return R.drawable.pdf;
        }
        return R.drawable.txt;
    }

    public List<Home> getListHome() {
        List<Home> lstHome = new ArrayList<>();
        lstHome.add(new Home("pdf"));
        lstHome.add(new Home("word"));
        lstHome.add(new Home("excel"));
        lstHome.add(new Home("powerpoint"));
        lstHome.add(new Home("html"));
        lstHome.add(new Home("text"));
        return lstHome;
    }

    public List<String> getExtension() {
        List<String> lstExtension = new ArrayList<>();
        switch (name) {
            case "pdf":
                lstExtension.add(".pdf");
                break;
            case "word":
                lstExtension.add(".doc");
                lstExtension.add(".docx");
                break;
            case "excel":
                lstExtension.add(".xls");
                lstExtension.add(".xlsx");
                break;
            case "powerpoint":
                lstExtension.add(".ppt");
                lstExtension.add(".pptx");
                break;
            case "html":
                lstExtension.add(".html");
                break;
            case "text":
                lstExtension.add(".txt");
                break;
        }

        return lstExtension;

    }
}
