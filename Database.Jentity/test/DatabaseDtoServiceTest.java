/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tableDto.DatabaseDtoService;
import tableDto.DtoSqlField;
import tableDto.IDatabaseDtoSqlService;
import testDtos.DtoTest;

/**
 *
 * @author marian
 */
public class DatabaseDtoServiceTest {
    
    public DatabaseDtoServiceTest() {
    }
    
    @Test
    public void GetSqlFields_ShouldReturnFields() throws Exception
    {
        IDatabaseDtoSqlService sut = createSut();
        Collection<DtoSqlField> result = sut.GetSqlFields(new DtoTest());
        
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(1, 
                result.stream().filter(dto -> dto.getName().equals("annoName")).count());
        Assert.assertEquals(0, 
                result.stream().filter(dto -> dto.getName().equals("ShouldBeIgnored")).count());
        
    }
        

    private IDatabaseDtoSqlService createSut() {
        return new DatabaseDtoService();
    }
}
