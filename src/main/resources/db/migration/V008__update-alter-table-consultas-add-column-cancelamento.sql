alter table consultas add status varchar(20) not null;
alter table consultas add motivo_cancelamento varchar(255);

update consultas set status = 'ABERTA';