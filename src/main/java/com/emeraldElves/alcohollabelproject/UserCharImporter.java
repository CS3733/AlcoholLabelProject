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
    public List<SubmittedApplication> decode(InputStream ifstream) throws IllegalFormatException, IOException {
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
            ApplicationEntity app = new ApplicationModel().create();
            for (int i = 0; i < fieldValues.size(); i++){
                String cellValue = unescapeStr(fieldValues.get(i).trim());
                System.out.println("Cell:" + cellValue);
                switch (columnTitles.get(i)){
                    case "alcoholcontent":
                        app.getAlcoholEntity().setAlcoholContent(Integer.parseInt(cellValue));
                        break;
                    case "submitdate":
                        app.setSubmissionTime(Long.parseLong(cellValue));
                        break;
                    case "issuedate": //aproval date?
                        app.setApprovalTime(Long.parseLong(cellValue));
                        break;
                    case "status":
                        app.setStatusCode(Integer.parseInt(cellValue));
                        break;
                    case "qualifications":
                        app.setQualifications(cellValue);
                        break;
                    case "fancifulName":
                        app.getAlcoholEntity().setFancifulName(cellValue);
                        break;
                    case "brandName":
                        app.getAlcoholEntity().setBrandName(cellValue);
                        break;
                    case "origin":
                        app.getAlcoholEntity().setOrigin(ProductSource.fromString(cellValue));
                        break;
                    case "type":
                        app.getAlcoholEntity().setType(AlcoholType.fromString(cellValue));
                        break;
                    case "formula":
                        app.getAlcoholEntity().setFormula(cellValue);
                        break;
                    case "serial":
                        app.getAlcoholEntity().setSerialNumber(cellValue);
                        break;
                    case "pH":
                        app.getAlcoholEntity().setPH(Double.parseDouble(cellValue));
                        break;
                    case "vintageyear":
                        app.getAlcoholEntity().setVintageYear(Integer.parseInt(cellValue));
                        break;
                    case "appellation":
                        app.getAlcoholEntity().setWineAppellation(cellValue);
                        break;
                    case "varietals":
                        app.getAlcoholEntity().setVarietals(cellValue);
                        break;
                    default:
                        throw new UnknownFormatConversionException("Unrecognized column in imports");
                }
            }
            app.save();
        }
        return new ArrayList<SubmittedApplication>();
    }
}
