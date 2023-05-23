function FarmerFormTest() {
    return (
        <>
            <form>
                <div class="form-group">
                    <label for="productName" class="form-label">Product</label>
                    <input type="text" class="form-control" id="productName" />
                    <div id="formGuide" class="formGuide">
                        (Example: Broccoli, Eggs, Asparagus...)
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="form-label">Price</label>
                    <input type="number" class="form-control" id="price" min="0.00" max="20.00" step="0.01" placeholder="0.00" />
                </div>
                <div class="form-group">
                    <label for="amount" class="form-label">Per</label>
                    <select id="amount" class="form-select" aria-label="Default select example">
                        <option selected></option>
                        <option value="oz">.oz</option>
                        <option value="gallon">gallon</option>
                        <option value="pound">.lb</option>
                        <option value="count">count</option>
                    </select>
                </div>
            </form>
            <button type="button" class="btn btn-secondary"> Add More
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
                </svg>
            </button>
        </>
    )
}

export default FarmerFormTest;