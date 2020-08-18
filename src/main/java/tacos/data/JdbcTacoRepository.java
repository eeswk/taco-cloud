//package tacos.data;
//
//import java.sql.Timestamp;
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.Date;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//import tacos.Ingredient;
//import tacos.Taco;
//
//@Slf4j
//@Repository
//public class JdbcTacoRepository implements TacoRepository {
//    private final JdbcTemplate jdbc;
//
//    public JdbcTacoRepository(JdbcTemplate jdbc) {
//        this.jdbc = jdbc;
//    }
//
//    @Override
//    public Taco save(Taco taco) {
//        long tacoId = saveTacoInfo(taco);
//        taco.setId(tacoId);
//        for (Ingredient ingredient : taco.getIngredients()) {
//            saveIngredientToTaco(ingredient, tacoId);
//        }
//        return taco;
//    }
//
//    private long saveTacoInfo(Taco taco) {
//        taco.setCreatedAt(new Date());
//
//        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
//                "insert into Taco (name, createdAt) values ( ?, ? )",
//                Types.VARCHAR, Types.TIMESTAMP);
//        pscf.setReturnGeneratedKeys(true);
//
//        PreparedStatementCreator psc =pscf.newPreparedStatementCreator(
//                Arrays.asList(
//                        taco.getName(),
//                        new Timestamp(taco.getCreatedAt().getTime())
//                )
//        );
//
////        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
////                "insert into Taco (name, createdAt) values ( ?, ? )",
////                Types.VARCHAR, Types.TIMESTAMP
////        ).newPreparedStatementCreator(
////                Arrays.asList(
////                        taco.getName(),
////                        new Timestamp(taco.getCreatedAt().getTime())
////                )
////        );
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        log.info("keyHolder before: " + keyHolder);
//        jdbc.update(psc, keyHolder);
//        log.info("keyHolder after: " + keyHolder);
//        log.info("keyHolder after: " + keyHolder.getKey().longValue());
//        return keyHolder.getKey().longValue();
//    }
//
//    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
//        log.info("tacoId->" + tacoId);
//        jdbc.update("insert into Taco_Ingredients (taco, ingredient) values ( ?, ? )",
//        tacoId, ingredient.getId());
//    }
//}
