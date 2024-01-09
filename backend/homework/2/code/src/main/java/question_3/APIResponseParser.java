package question_3;

import org.example.Logg;
import org.slf4j.Logger;

public class APIResponseParser {
    public static String parse (String response, String startRule, String endRule){
        int pos=response.indexOf(startRule);
        int start=pos+startRule.length();
        int end=response.indexOf(endRule,start);

        return response.substring(start,end);
    }
    public static Book parse(String response){

        Book book=new Book();

        String endRule1="<";
        String startRule1="<title>";
        String title=parse(response,startRule1,endRule1);
        book.setTitle(title);

        String endRule2="<";
        String startRule2="<name>";
        String author=parse(response,startRule2,endRule2);
        book.setAuthor(author);

        String endRule3="<";
        String startRule3="<original_publication_year type=\"integer\">";
        String publicationYear=parse(response,startRule3,endRule3);
        publicationYear=publicationYear.replaceAll(",","");
        book.setPublicationYear(Integer.parseInt(publicationYear));

        String endRule4="<";
        String startRule4="<average_rating>";
        String avgRating=parse(response,startRule4,endRule4);
        avgRating=avgRating.replaceAll(",","");
        book.setRatingsCount((int) Double.parseDouble(avgRating));

        String endRule5="<";
        String startRule5="<ratings_count type=\"integer\">";
        String ratingsCount=parse(response,startRule5,endRule5);
        ratingsCount=ratingsCount.replaceAll(",","");
        book.setRatingsCount(Integer.parseInt(ratingsCount));

        String endRule6="<";
        String startRule6="<image_url>";
        String imgUrl=parse(response,startRule6,endRule6);
        book.setImageUrl(imgUrl);
        Logg.logger.info(book.getAuthor());
        Logg.logger.info(book.getTitle());
        Logg.logger.info(book.getImageUrl());
        return book;
    }

    }
