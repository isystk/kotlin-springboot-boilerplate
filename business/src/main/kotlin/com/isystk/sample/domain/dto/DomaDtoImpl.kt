package com.isystk.sample.domain.dto

import com.isystk.sample.domain.dao.DefaultEntityListener
import org.seasar.doma.Entity
import java.io.Serializable

@Entity(listener = DefaultEntityListener::class) // 自動的にシステム制御項目を更新するためにリスナーを指定する
abstract class DomaDtoImpl : DomaDto, Serializable