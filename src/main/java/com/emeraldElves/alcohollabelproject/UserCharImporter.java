package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Essam on 4/17/2017.
 */
public class UserCharImporter implements IImporter {
    char delim;

    public UserCharImporter(char delim) throws IllegalArgumentException {
        this.delim = delim;
    }
    //unescapes a single line/row
    private String unescapeStr(String str){
        //StringEscapeUtils.
        return str;
    }
    //throws IllegalFormatException if format isn't as expected with selected delim
    public List<BaseEntity> decode(InputStream ifstream) throws IllegalFormatException, IOException {
        List<String> lines = IOUtils.readLines(ifstream);
        //Pattern p = Pattern.compile("(?:[,]|^)[ ]*(?:(?<openquote>)(?<!\\\\)\")?(?(openquote)(?:(.*?)(?<!\\\\)\")|([\\-+0-9.]*+))[ ]*?(?:$)*?");
        List<String> columnTitles = Arrays.asList(lines.remove(0).split(String.valueOf(delim)));
        for (String title : columnTitles){
            System.out.println(title);
        }
        for (String line : lines){
            //(?:[,]|^)[ ]*(?:(?<openquote>)(?<!\\)")?(?(openquote)(?:(.*?)(?<!\\)")|([\-+0-9.]*+))[ ]*?(?:$)*?
            //This reads delimiter seperated values as strings, and ignores whitespace around the value.
            //Quoted values are treated as Strings, Unquoted values are treated as Float
            //Making this regex thing took me so many hours, you guys better give me WPI dollars
            //Edit: Holy shit, "conditional references not supported". Por que Dios.
            List<String> fieldValues = Arrays.asList(line.split("(?<=(?:(?<!\\\\)\"|[a-zA-Z0-9_.\\-+]))[ ]*" + Pattern.quote(String.valueOf(delim)) + "{1,}[ ]*"));


        }
        return new ArrayList<BaseEntity>();
    }
}
