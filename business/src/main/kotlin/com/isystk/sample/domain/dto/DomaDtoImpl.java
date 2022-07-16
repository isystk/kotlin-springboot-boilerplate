package com.isystk.sample.domain.dto;

import com.isystk.sample.domain.dao.DefaultEntityListener;
import java.io.Serializable;
import org.seasar.doma.Entity;

@SuppressWarnings("serial")
@Entity(listener = DefaultEntityListener.class) // 自動的にシステム制御項目を更新するためにリスナーを指定する
public abstract class DomaDtoImpl implements DomaDto, Serializable {

}
