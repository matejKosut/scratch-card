package sk.matejkosut.scratchcard.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import sk.matejkosut.scratchcard.data.source.local.ScratchCard

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultScratchCardRepositoryTest {

    private val scratchCardCode1 = 10
    private val scratchCardState1 = 1

    private val scratchCardCode2 = 20
    private val scratchCardState2 = 2

    private lateinit var networkDataSource: FakeNetworkDataSource
    private lateinit var localDataSource: FakeLocalDataSource

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    private lateinit var scratchCardRepository: DefaultScratchCardRepository

    @Before
    fun createRepository() {
        networkDataSource = FakeNetworkDataSource()
        localDataSource = FakeLocalDataSource(ScratchCard(scratchCardCode1, scratchCardState1))
        scratchCardRepository = DefaultScratchCardRepository(
            networkDataSource = networkDataSource,
            localDataSource = localDataSource,
            dispatcher = testDispatcher,
            scope = testScope
        )
    }

    @Test
    fun scratchCardRepository_createScratchCard() = testScope.runTest {
        scratchCardRepository.createScratchCard(scratchCardCode2, scratchCardState2)
        val code = scratchCardRepository.getScratchCardCode()
        val state = scratchCardRepository.getScratchCardState()
        assert(code == scratchCardCode2)
        assert(state == scratchCardState2)
    }

    @Test
    fun scratchCardRepository_getScratchCardCode() = testScope.runTest {
        val code = scratchCardRepository.getScratchCardCode()
        assert(code == scratchCardCode1)
    }

    @Test
    fun scratchCardRepository_getScratchCardState() = testScope.runTest {
        val state = scratchCardRepository.getScratchCardState()
        assert(state == scratchCardState1)
    }

    @Test
    fun scratchCardRepository_updateScratchCardState() = testScope.runTest {
        scratchCardRepository.updateScratchCardState(scratchCardState2, scratchCardCode1)
        val code = scratchCardRepository.getScratchCardCode()
        val state = scratchCardRepository.getScratchCardState()
        assert(state == scratchCardState2)
        assert(code == scratchCardCode1)
    }

    @Test
    fun scratchCardRepository_activateScratchCardEqual() = testScope.runTest {
        val response = scratchCardRepository.activateScratchCard(FakeNetworkDataSource.CODE1)
        assert(response.not())
    }

    @Test
    fun scratchCardRepository_activateScratchCardHigher() = testScope.runTest {
        val response = scratchCardRepository.activateScratchCard(FakeNetworkDataSource.CODE2)
        assert(response)
    }

    @Test
    fun scratchCardRepository_activateScratchCardLower() = testScope.runTest {
        val response = scratchCardRepository.activateScratchCard(FakeNetworkDataSource.CODE3)
        assert(response.not())
    }

    @Test
    fun scratchCardRepository_activateScratchCardError() = testScope.runTest {
        val response = scratchCardRepository.activateScratchCard(0)
        assert(response.not())
    }

}