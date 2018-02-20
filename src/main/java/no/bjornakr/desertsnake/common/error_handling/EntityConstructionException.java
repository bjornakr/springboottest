package no.bjornakr.desertsnake.common.error_handling;

public class EntityConstructionException extends IllegalArgumentException {

    public EntityConstructionException(Class entity, String message) {
        super(String.format("Could not construct entity %s: %s", entity.getName(), message));
    }
}
