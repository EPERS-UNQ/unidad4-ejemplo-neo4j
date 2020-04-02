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
        val jerry = Persona("300000", "Jerry", "Smith")
        val morty = Persona("300001", "Morty", "Smith")

        dao.create(jerry)
        dao.create(morty)

        dao.crearRelacionEsHijoDe(jerry, morty)
    }

    @Test
    fun hijosDe() {
        val jerry = Persona("300000", "Jerry", "Smith")
        val morty = Persona("300001", "Morty", "Smith")
        val summer = Persona("300002", "Summer", "Smith")

        dao.create(jerry)
        dao.create(summer)
        dao.create(morty)

        dao.crearRelacionEsHijoDe(jerry, morty)
        dao.crearRelacionEsHijoDe(jerry, summer)

        val hijos = dao.getHijosDe(jerry)

        Assert.assertEquals(2, hijos.size.toLong())
        Assert.assertTrue(hijos.contains(morty))
        Assert.assertTrue(hijos.contains(summer))
    }
}