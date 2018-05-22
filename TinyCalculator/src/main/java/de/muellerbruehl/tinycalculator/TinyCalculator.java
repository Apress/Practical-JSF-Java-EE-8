/***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 ***********************************************************/
package de.muellerbruehl.tinycalculator;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class TinyCalculator implements Serializable{

    private static final Logger _logger = Logger.getLogger("TinyCalculator");

    public TinyCalculator() {
        _logger.log(Level.INFO, "ctor TinyCalculator");
    }
    private double _param1;
    private double _param2;
    private double _result;

    public double getParam1() {
        return _param1;
    }

    public void setParam1(double param1) {
        _param1 = param1;
    }

    public double getParam2() {
        return _param2;
    }

    public void setParam2(double param2) {
        _param2 = param2;
    }

    public double getResult() {
        return _result;
    }

    public void setResult(double result) {
        _result = result;
    }

    public String add() {
        _result = _param1 + _param2;
        return "";
    }

    public String subtract() {
        _result = _param1 - _param2;
        return "";
    }

    public String multiply() {
        _result = _param1 * _param2;
        return "";
    }

    public String divide() {
        _result = _param1 / _param2;
        return "";
    }

    public String divideInt(int p1, int p2) {
        _result = p1 / p2;
        return "";
    }
}
