/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableDto;

import java.util.Collection;

/**
 *
 * @author marian
 */
public interface IDatabaseDtoSqlService {
    <TDto> Collection<DtoSqlField> GetSqlFields(TDto dto) throws Exception;
    <TDto> Collection<DtoSqlField> GetSqlFields(Class<TDto> type) throws Exception;
}
