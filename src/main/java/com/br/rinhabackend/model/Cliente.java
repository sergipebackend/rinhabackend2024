package com.br.rinhabackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("clientes")
public record Cliente(@Id int id, long limite, long saldo) {}