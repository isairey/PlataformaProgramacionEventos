package org.ess.module.asset.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AssetModel {

    private long id;

    private String name;

    private String category;

    private int quantity;

    private String condition;

    private String status;

    private double price;

    private String createdAt;

    private String updatedAt;
}