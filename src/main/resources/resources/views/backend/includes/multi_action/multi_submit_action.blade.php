@can("edit_{$moduleName}")
    <button class='btn btn-success' wire:click="updateMultiple('submit')" type="button" data-btn="submit">
        <i class="fas fa-save"></i> {{__("Submit")}}
    </button>
@endcan
