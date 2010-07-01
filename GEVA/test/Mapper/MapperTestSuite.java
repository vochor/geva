package Mapper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test the original Mapper packages 
 * @author erikhemberg
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ContextFreeGrammarTest.class,
    MapperTest.class,
    GrammarTest.class,
    GEGrammarTest.class,
    DerivationTreeTest.class,
    RuleTest.class,
    ProductionTest.class,
    SymbolTest.class
})
        
public class MapperTestSuite {
}