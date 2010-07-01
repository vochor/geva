/*
 * Operator.java
 *
 * Created on October 26, 2006, 12:06 PM
 *
 */

package Operator;

import Operator.Operations.Operation;

/**
 * Interface for Operator. The Opertor can get and set an operation.
 * @author erikhemberg
 */
public interface Operator extends Module {

    /**
     * Set operation that operator performs
     * @param op operation
     */
    public void setOperation(Operation op);

    /**
     * Get operation that operator performs
     * @return operation
     */
    public Operation getOperation();
}
