package org.solidarizr.agent.utils;

import org.solidarizr.agent.connector.model.Category;

public class CategoryFixtures {
    public static final Category EXISTING_CATEGORY = Category.builder()
            .id(1)
            .name("Doações").build();

    public static final Category ANOTHER_EXISTING_CATEGORY = Category.builder()
            .id(2)
            .name("Projetos Recorrentes").build();;
}
