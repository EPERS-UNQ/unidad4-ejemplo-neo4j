package ar.edu.unq.epers.unidad4

import ar.edu.unq.epers.unidad4.dao.PersonaNeo4jDAO
import ar.edu.unq.epers.unidad4.model.Persona
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PersonaNeo4jDAOTest {
    lateinit var dao: PersonaNeo4jDAO

    @Before
    fun setUp() {
        dao = PersonaNeo4jDAO()
    }

    @After
    fun after() {
        dao.clear()
    }

    @Test
    fun crearPersona() {
        val persona = Persona("300000", "Jerry", "Smith")
        dao.create(persona)
        val persona2 = Persona("300001", "Morty", "Smith")
        dao.create(persona2)
        dao.crearRelacionEsHijoDe(persona, persona2)
    }

    @Test
    fun hijosDe() {
        val persona = Persona("300000", "Jerry", "Smith")
        dao.create(persona)
        val persona2 = Persona("300001", "Morty", "Smith")
        dao.create(persona2)
        val persona3 = Persona("300002", "Summer", "Smith")
        dao.create(persona3)
        dao.crearRelacionEsHijoDe(persona, persona2)
        dao.crearRelacionEsHijoDe(persona, persona3)
        val hijos = dao.getHijosDe(persona)
        Assert.assertEquals(2, hijos.size.toLong())
        Assert.assertTrue(hijos.contains(persona2))
        Assert.assertTrue(hijos.contains(persona3))
    }
}