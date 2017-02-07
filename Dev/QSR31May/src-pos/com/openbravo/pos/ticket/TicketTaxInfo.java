//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.ticket;

import com.openbravo.format.Formats;
import java.util.Map;


public class TicketTaxInfo {
    
    private TaxInfo tax;
    public TicketInfo ticket;
    public RetailTicketInfo retailTicket;
    private double subtotal;
    private double taxtotal;
    private double taxAmount;
    private double retailTaxTotal;
    private double retailSBTaxTotal;
    private double retailServiceTaxTotal;
     private double retailSwachBharatTaxTotal;
    private double retailTaxesLineTotal;
    private double taxbasetotal;
    private double servicetaxbasetotal;
    private double swatchBharatbasetotal;
            
    /** Creates a new instance of TicketTaxInfo */
    public TicketTaxInfo(TaxInfo tax) {
        this.tax = tax;
        subtotal = 0.0;
        taxtotal = 0.0;
        retailTaxTotal = 0.0;
        taxbasetotal=0;
        servicetaxbasetotal=0;
        swatchBharatbasetotal=0;
    }
    
    public TaxInfo getTaxInfo() {
        return tax;
    }

    public TicketInfo getTicketInfo() {
        return ticket;
    }
      public RetailTicketInfo getRetailTicketInfo() {
        return retailTicket;
    }
    public void add(double dValue) {
       // subtotal += dValue;
      //  taxtotal = (subtotal * tax.getRate());
      // retailTaxTotal =  ((subtotal+(subtotal*RetailTicketInfo.getServiceChargeRate())) * tax.getRate());
     }
  
  //Toit  
  public void add(double dValue,RetailTicketInfo ticket) {
       // subtotal += dValue;
          subtotal = dValue;
          taxbasetotal+=dValue;
        taxtotal = (subtotal * tax.getRate());
        System.out.println("subtotal "+subtotal+" ticket.getServiceChargeRate()"+ticket.getServiceChargeRate()+"  tax.getRate()"+ tax.getRate());
        //Calculating present item tax total
        retailTaxTotal =  ((subtotal+(subtotal*ticket.getServiceChargeRate())) * tax.getRate());
         //Summing  present item tax total with previous tax total
        retailTaxesLineTotal=retailTaxesLineTotal+retailTaxTotal;

    }
  
     //WMC- Brewpub and WMC- Terrace Tax will be calculated based on subtotal(service charge will not be considered)
     public void addTaxWithoutServiceCharge(double dValue,RetailTicketInfo ticket) {
        // subtotal += dValue;
        subtotal = dValue;
         taxbasetotal+=dValue;
        taxtotal = (subtotal * tax.getRate());
        System.out.println("addTaxWithoutServiceCharge subtotal "+subtotal+" ticket.getServiceChargeRate()"+ticket.getServiceChargeRate()+"  tax.getRate()"+ tax.getRate());
        //Calculating present item tax total
        retailTaxTotal =  subtotal * tax.getRate();
         //Summing  present item tax total with previous tax total
        retailTaxesLineTotal=retailTaxesLineTotal+retailTaxTotal;

    }
     
     //Toit and WMC- Brewpub
    public void addServiceTax(double dValue,RetailTicketInfo ticket) {
        subtotal = dValue;
        servicetaxbasetotal+=dValue;
        taxtotal = (subtotal * tax.getRate());
         System.out.println("subtotal "+subtotal+" ticket.getServiceChargeRate()"+ticket.getServiceChargeRate()+"  tax.getRate()"+ tax.getRate());
       //Calculating present item service tax total
        retailTaxTotal =  ((subtotal+(subtotal*ticket.getServiceChargeRate())) * tax.getRate());
       //Calculating present item service tax total
         System.out.println("retailServiceTaxTotal "+retailServiceTaxTotal+" retailTaxTotal "+retailTaxTotal);
       retailServiceTaxTotal=retailServiceTaxTotal+retailTaxTotal;
    }
   
    //WMC- Terrace  service tax will be calculated taking only service charge.(subtotal is not considered)
 public void addServiceTaxOnServiceCharge(double dValue,RetailTicketInfo ticket) {
        subtotal = dValue;
        servicetaxbasetotal+=dValue;
        taxtotal = (subtotal * tax.getRate());
        System.out.println("testing terrace "+dValue+" rate "+ticket.getServiceChargeRate()+" tax.getRate() "+tax.getRate() );
       //Calculating present item service tax total
       // retailTaxTotal = (ticket.getServiceChargeRate()*100) * tax.getRate();
         retailTaxTotal = (dValue*ticket.getServiceChargeRate()) * tax.getRate();
       //Calculating present item service tax total
       retailServiceTaxTotal=retailServiceTaxTotal+retailTaxTotal;
    }
  public void addSwachBharatTax(double dValue,RetailTicketInfo ticket) {
      System.out.println("entere addswachbharat");
        subtotal = dValue;
        swatchBharatbasetotal+=dValue;
        taxtotal = (subtotal * tax.getRate());
         System.out.println("subtotal "+subtotal+" addSwachBharatTax)"+ tax.getRate());
       //Calculating present item service tax total
        retailSBTaxTotal =  ((subtotal+(subtotal*ticket.getServiceChargeRate())) * tax.getRate());
       //Calculating present item service tax total
         System.out.println("retailServiceTaxTotal "+retailSBTaxTotal+" retailTaxTotal "+retailSBTaxTotal);
       retailSwachBharatTaxTotal=retailSwachBharatTaxTotal+retailSBTaxTotal;
    }

    //WMC- Terrace  service tax will be calculated taking only service charge.(subtotal is not considered)
 public void addSwachBharatOnServiceCharge(double dValue,RetailTicketInfo ticket) {
        subtotal = dValue;
        swatchBharatbasetotal+=dValue;
        taxtotal = (subtotal * tax.getRate());
        System.out.println("testing terrace "+dValue+" rate "+ticket.getServiceChargeRate()+" tax.getRate() "+tax.getRate() );
       //Calculating present item service tax total
       // retailTaxTotal = (ticket.getServiceChargeRate()*100) * tax.getRate();
         retailSBTaxTotal = (dValue*ticket.getServiceChargeRate()) * tax.getRate();
       //Calculating present item service tax total
       retailSwachBharatTaxTotal=retailSwachBharatTaxTotal+retailTaxTotal;
    }
 
    //Developed by Shilpa
    // implementing erp tax calculation
    public void addErpStdTax(double dValue, RetailTicketInfo ticket, Map<String, TaxStructureInfo> taxMap) {
        subtotal = dValue;
        taxbasetotal += dValue;
        //if calculation type is LNA (Line Amount) 
        if (tax.getBaseAmt().equals("LNA")) {
            retailTaxTotal = subtotal * tax.getRate();
            taxMap.put(tax.getId(), new TaxStructureInfo(tax.getBaseAmt(), tax.getRate(), retailTaxTotal, true));
        } else {
             //if calculation type is LNATAX/TAX (Line Amount+Tax) /Tax
            retailTaxTotal = calculateTaxValue(taxMap, tax, tax.getId());
            // retailTaxTotal =(taxMap.get(tax.getTaxBaseId()).getTaxValue()+subtotal)*tax.getRate();
        }
         retailTaxesLineTotal = retailTaxesLineTotal + retailTaxTotal;
        

    }
     //Developed by Shilpa
    public double calculateTaxValue(Map<String, TaxStructureInfo> taxMap, TaxInfo tax, String taxId) {
        String calculationType = taxMap.get(taxId).getCalculationType();
        double taxValue = 0;
        if (calculationType.equals("LNA")) {
            taxValue = (taxMap.get(taxId).getTaxRate() * subtotal);
            return taxValue;
        } else  if (calculationType.equals("LNATAX")) {
            double baseTaxTotal = 0;
            //If it already calculated
            if (taxMap.get(taxId).isCalculated()) {
                taxValue = taxMap.get(taxId).getTaxValue();
           } else {
                baseTaxTotal = calculateTaxValue(taxMap, tax, tax.getTaxBaseId());
                 taxValue = (subtotal + baseTaxTotal) * tax.getRate();
             }
          taxMap.put(taxId, new TaxStructureInfo(calculationType, tax.getRate(), taxValue, true));

            return taxValue;
        }
        
        //This is for calculationType=tax
        else {
            double baseTaxTotal;
            //If it already calculated
           if (taxMap.get(taxId).isCalculated()) {
                taxValue = taxMap.get(tax.getTaxBaseId()).getTaxValue();
            } else {
                baseTaxTotal = calculateTaxValue(taxMap, tax, tax.getTaxBaseId());
                 taxValue = (baseTaxTotal) * tax.getRate();
            }
              taxMap.put(taxId, new TaxStructureInfo(calculationType, tax.getRate(), taxValue, true));
            return taxValue;
        }
        


    }
    public double getSubTotal() {    
        return subtotal;
    }
    
    public double getTax() {       
        return taxtotal;
    }
    public double getRetailTax() {
      //  return retailTaxTotal;
        return retailTaxesLineTotal;
    }
    
    public double getRetailServiceTax() {
        return retailServiceTaxTotal;
    }
   public double getRetailSwachBharatTax() {
        return retailSwachBharatTaxTotal;
    }
    public double getTotal() {         
        return subtotal + taxtotal;
    }
    public double getRetailTotal() {
        return subtotal + retailTaxTotal;
    }
    public double getTaxAmount() {
        return taxAmount;
    }
    public void setTaxAmount(double taxAmount){
        this.taxAmount = taxAmount;
    }
    public String printSubTotal() {
        return Formats.CURRENCY.formatValue(new Double(getSubTotal()));
    }
    public String printTax() {
        return Formats.CURRENCY.formatValue(new Double(getTax()));
    }
    public String printRetailTax() {
        return Formats.CURRENCY.formatValue(new Double(getRetailTax()));
    }
    public String printTotal() {
        return Formats.CURRENCY.formatValue(new Double(getTotal()));
    }
     public String printRetailTotal() {
        return Formats.CURRENCY.formatValue(new Double(getRetailTotal()));
    }
     
      public String printRetailServiceTax() {
        return Formats.CURRENCY.formatValue(new Double(getRetailServiceTax()));
    }
      public String printRetailSwachBharatTax() {
        return Formats.CURRENCY.formatValue(new Double(getRetailSwachBharatTax()));
    }
      
      public double getTaxBaseTotal() {    
        return taxbasetotal;
    }
      
       public double getServiceTaxBaseTotal() {    
        return servicetaxbasetotal;
    }
        public double getSwachBharatBaseTotal() {
        return swatchBharatbasetotal;
    }
}
