package io.github.pmartycz.ghinfo.repo;

interface RepositoriesFactory {

    /**
     * Returns repositories of specified owner.
     */
    Repositories ownerRepositories(String owner);

}
