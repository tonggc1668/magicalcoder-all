package com.magicalcoder.youyaboot.model;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
import java.io.Serializable;
import lombok.Data;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
@Data
public class AllType implements Serializable{

    private Long longId;//主键
    private byte[] bytesBinary;//bytes_binary
    private byte[] bytesBlob;//bytes_blob
    private byte[] bytesMediumblob;//bytes_mediumblob
    private byte[] bytesVarbinary;//bytes_varbinary
    private byte[] bytesTinyblob;//bytes_tinyblob
    private byte[] bytesLongblob;//bytes_longblob
    private Boolean booleanBit;//boolean_bit
    private Boolean booleanBool;//boolean_bool
    private Boolean booleanBoolean;//boolean_boolean
    private String stringEnum;//string_enum
    private String stringChar;//string_char
    private String stringLongtext;//string_longtext
    private String stringMediumtext;//string_mediumtext
    private String stringVarchar;//string_varchar
    private String stringSet;//string_set
    private String stringTinytext;//string_tinytext
    private String stringText;//string_text
    private Byte byteTinyint;//byte_tinyint
    private Short shortSmallint;//short_smallint
    private Integer integerInt;//integer_int
    private Integer integerMediumint;//integer_mediumint
    private Float floatFloat;//float_float
    private Double doubleDouble;//double_double
    private Double doubleReal;//double_real
    private BigDecimal bigdecimalDecimal;//bigdecimal_decimal
    private BigDecimal bigdecimalNumeric;//bigdecimal_numeric
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date dateDate;//date_date
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date dateYear;//date_year
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Time timeTime;//time_time
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp timestempDatetime;//timestemp_datetime
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp timestempTimestemp;//timestemp_timestemp


    public Long getLongId(){
        return longId;
    }
    public void setLongId(Long longId){
        this.longId = longId;
    }

    public byte[] getBytesBinary(){
        return bytesBinary;
    }
    public void setBytesBinary(byte[] bytesBinary){
        this.bytesBinary = bytesBinary;
    }

    public byte[] getBytesBlob(){
        return bytesBlob;
    }
    public void setBytesBlob(byte[] bytesBlob){
        this.bytesBlob = bytesBlob;
    }

    public byte[] getBytesMediumblob(){
        return bytesMediumblob;
    }
    public void setBytesMediumblob(byte[] bytesMediumblob){
        this.bytesMediumblob = bytesMediumblob;
    }

    public byte[] getBytesVarbinary(){
        return bytesVarbinary;
    }
    public void setBytesVarbinary(byte[] bytesVarbinary){
        this.bytesVarbinary = bytesVarbinary;
    }

    public byte[] getBytesTinyblob(){
        return bytesTinyblob;
    }
    public void setBytesTinyblob(byte[] bytesTinyblob){
        this.bytesTinyblob = bytesTinyblob;
    }

    public byte[] getBytesLongblob(){
        return bytesLongblob;
    }
    public void setBytesLongblob(byte[] bytesLongblob){
        this.bytesLongblob = bytesLongblob;
    }

    public Boolean getBooleanBit(){
        return booleanBit;
    }
    public void setBooleanBit(Boolean booleanBit){
        this.booleanBit = booleanBit;
    }

    public Boolean getBooleanBool(){
        return booleanBool;
    }
    public void setBooleanBool(Boolean booleanBool){
        this.booleanBool = booleanBool;
    }

    public Boolean getBooleanBoolean(){
        return booleanBoolean;
    }
    public void setBooleanBoolean(Boolean booleanBoolean){
        this.booleanBoolean = booleanBoolean;
    }

    public String getStringEnum(){
        return stringEnum;
    }
    public void setStringEnum(String stringEnum){
        this.stringEnum = stringEnum;
    }

    public String getStringChar(){
        return stringChar;
    }
    public void setStringChar(String stringChar){
        this.stringChar = stringChar;
    }

    public String getStringLongtext(){
        return stringLongtext;
    }
    public void setStringLongtext(String stringLongtext){
        this.stringLongtext = stringLongtext;
    }

    public String getStringMediumtext(){
        return stringMediumtext;
    }
    public void setStringMediumtext(String stringMediumtext){
        this.stringMediumtext = stringMediumtext;
    }

    public String getStringVarchar(){
        return stringVarchar;
    }
    public void setStringVarchar(String stringVarchar){
        this.stringVarchar = stringVarchar;
    }

    public String getStringSet(){
        return stringSet;
    }
    public void setStringSet(String stringSet){
        this.stringSet = stringSet;
    }

    public String getStringTinytext(){
        return stringTinytext;
    }
    public void setStringTinytext(String stringTinytext){
        this.stringTinytext = stringTinytext;
    }

    public String getStringText(){
        return stringText;
    }
    public void setStringText(String stringText){
        this.stringText = stringText;
    }

    public Byte getByteTinyint(){
        return byteTinyint;
    }
    public void setByteTinyint(Byte byteTinyint){
        this.byteTinyint = byteTinyint;
    }

    public Short getShortSmallint(){
        return shortSmallint;
    }
    public void setShortSmallint(Short shortSmallint){
        this.shortSmallint = shortSmallint;
    }

    public Integer getIntegerInt(){
        return integerInt;
    }
    public void setIntegerInt(Integer integerInt){
        this.integerInt = integerInt;
    }

    public Integer getIntegerMediumint(){
        return integerMediumint;
    }
    public void setIntegerMediumint(Integer integerMediumint){
        this.integerMediumint = integerMediumint;
    }

    public Float getFloatFloat(){
        return floatFloat;
    }
    public void setFloatFloat(Float floatFloat){
        this.floatFloat = floatFloat;
    }

    public Double getDoubleDouble(){
        return doubleDouble;
    }
    public void setDoubleDouble(Double doubleDouble){
        this.doubleDouble = doubleDouble;
    }

    public Double getDoubleReal(){
        return doubleReal;
    }
    public void setDoubleReal(Double doubleReal){
        this.doubleReal = doubleReal;
    }

    public BigDecimal getBigdecimalDecimal(){
        return bigdecimalDecimal;
    }
    public void setBigdecimalDecimal(BigDecimal bigdecimalDecimal){
        this.bigdecimalDecimal = bigdecimalDecimal;
    }

    public BigDecimal getBigdecimalNumeric(){
        return bigdecimalNumeric;
    }
    public void setBigdecimalNumeric(BigDecimal bigdecimalNumeric){
        this.bigdecimalNumeric = bigdecimalNumeric;
    }

    public Date getDateDate(){
        return dateDate;
    }
    public void setDateDate(Date dateDate){
        this.dateDate = dateDate;
    }

    public Date getDateYear(){
        return dateYear;
    }
    public void setDateYear(Date dateYear){
        this.dateYear = dateYear;
    }

    public Time getTimeTime(){
        return timeTime;
    }
    public void setTimeTime(Time timeTime){
        this.timeTime = timeTime;
    }

    public Timestamp getTimestempDatetime(){
        return timestempDatetime;
    }
    public void setTimestempDatetime(Timestamp timestempDatetime){
        this.timestempDatetime = timestempDatetime;
    }

    public Timestamp getTimestempTimestemp(){
        return timestempTimestemp;
    }
    public void setTimestempTimestemp(Timestamp timestempTimestemp){
        this.timestempTimestemp = timestempTimestemp;
    }
}
