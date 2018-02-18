/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Collection;

/**
 *
 * @author marian
 */
public interface IJentity {
    <TDto>Collection<TDto> Select(String table,Class<TDto> type) throws Exception;
}
