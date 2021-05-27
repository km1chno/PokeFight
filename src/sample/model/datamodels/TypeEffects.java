package sample.model.datamodels;

public class TypeEffects {
    public String name;

    public static class DamageRelations {
        public Result[] double_damage_to;
        public Result[] half_damage_to;
        public Result[] no_damage_to;
    }

    public DamageRelations damage_relations;
}
