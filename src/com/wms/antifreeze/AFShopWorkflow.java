/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze;

import com.wms.antifreeze.AFShopWorkflow.AFShopWorkFlowElement;
import com.wms.antifreeze.AFShopWorkflow.TicketStage;
import java.util.Stack;

/**
 *
 * @author Matt
 *
 * A class to allow an indivual shop to define how a ticket flows through
 * their shop. A workflow consists of an ordered number of steps that can be used
 * to regulate the "lifecycle" of tickets and invoices through that shop
 *
 *
 */



public class AFShopWorkflow extends Stack<TicketStage> {


    public enum TicketStage
    {
        Initialized,
        Reserved,  // a "virtual ticket"
        Open,
        CheckIn, // Gregg
        KeyIn, // Gregg
        Dispatched,
        Assigned,
        Worked,
        Estimate,
        CusAuth,
        Amended,
        ReAuth,
        CheckOut, // Gregg
        Paid,
        PO,
        Closed;
    }


    class AFShopWorkFlowElement
    {
        private TicketStage elementStage = TicketStage.Initialized;
        private boolean updateDatabase = false;
        private AFShopWorkFlowElement parentElement;
        private Stack<AFShopWorkFlowElement> sibilingElements;
        private AFShopWorkFlowElement childElement;

        public AFShopWorkFlowElement(TicketStage initStage )
        {
            elementStage = initStage;
        }

        /**
         * @return the elementStage
         */
        public TicketStage getElementStage() {
            return elementStage;
        }

        /**
         * @param elementStage the elementStage to set
         */
        public void setElementStage(TicketStage elementStage) {
            this.elementStage = elementStage;
        }

        /**
         * @return the updateDatabase
         */
        public boolean isUpdateDatabase() {
            return updateDatabase;
        }

        /**
         * @param updateDatabase the updateDatabase to set
         */
        public void setUpdateDatabase(boolean updateDatabase) {
            this.updateDatabase = updateDatabase;
        }

        /**
         * @return the parentElement
         */
        public AFShopWorkFlowElement getParentElement() {
            return parentElement;
        }

        /**
         * @param parentElement the parentElement to set
         */
        public void setParentElement(AFShopWorkFlowElement parentElement) {
            this.parentElement = parentElement;
        }

        public void addSibiling(AFShopWorkFlowElement sibiling)
        {
            this.sibilingElements.push(sibiling);
        }
        /**
         * @return the sibilingElements
         */
        public Stack<AFShopWorkFlowElement> getSibilingElements() {
            return sibilingElements;
        }

        /**
         * @param sibilingElements the sibilingElements to set
         */
        public void setSibilingElements(Stack<AFShopWorkFlowElement> sibilingElements) {
            this.sibilingElements = sibilingElements;
        }

        /**
         * @return the childElement
         */
        public AFShopWorkFlowElement getChildElement() {
            return childElement;
        }

        /**
         * @param childElement the childElement to set
         */
        public void setChildElement(AFShopWorkFlowElement childElement) {
            this.childElement = childElement;
        }
    }

    Stack<AFShopWorkFlowElement> workFlow = new Stack<AFShopWorkFlowElement>();

    public AFShopWorkflow()
    {

    }

    @Override
    public TicketStage push( TicketStage stage)
    {
        TicketStage pushStage;

        AFShopWorkFlowElement newElement = new AFShopWorkFlowElement( stage );

        pushStage = this.workFlow.push(newElement).getElementStage();
        return stage;
    }

    @Override
    public void addElement( TicketStage stage)
    {
        TicketStage pushStage;

        AFShopWorkFlowElement newElement = new AFShopWorkFlowElement( stage );

        workFlow.addElement(newElement);
    }



    public void setStageDBUpdate( TicketStage stage, boolean flag)
    {

    }


}
