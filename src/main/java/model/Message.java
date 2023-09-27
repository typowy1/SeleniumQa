package model;

import enums.MessageSubject;
import lombok.Data;

@Data //pusty construktor, gettery setery o string hascode equals, po lewo mozna kliknas w stukture klasy i zobaczyc
public class Message { //dla loombok, pamiętaj aby zaznaczyć w compoler/ anotation procesor/ enable annotation processing

    private MessageSubject subject;
    private String email;
    private String orderReference;
    private String message;
}
