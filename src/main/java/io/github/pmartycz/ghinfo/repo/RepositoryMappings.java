package io.github.pmartycz.ghinfo.repo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
interface RepositoryMappings {
    RepositoryMappings INSTANCE = Mappers.getMapper(RepositoryMappings.class);

    @Mapping(source = "stargazersCount", target = "stars")
    RepositoryModel clientToInternal(RepositoryClientModel model);
}
