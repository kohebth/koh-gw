<div class="row mt-4">
    <div class="col">
        <form wire:submit.prevent="save" class="form-horizontal content-loading">
            <x-loading/>
            {{ csrf_field()}}
            <div class="form-group row">
                <label class="col-sm-2 form-control-label" for="file">
                    Adblock file <span class="text-danger">(*.csv)</span>
                </label>
                <div class="col-sm-10">
                    <input type="file" class="form-control" wire:model="files" accept=".csv" multiple>
                    @error('files') <x-error-message>{{ $message }}</x-error-message> @enderror
                    @error('files.*') <x-error-message>{{ $message }}</x-error-message> @enderror
                </div>
            </div>

            <div class="form-group row">
                {{ html()->label('Type <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('type') }}
                <div class="col-sm-10">
                    <select wire:model="type" class="form-control">
                        <option value=""></option>
                        @foreach($this->types as $type)
                            <option value="{{$type}}">{{__($type)}}</option>
                        @endforeach
                    </select>
                    @error('type')<x-error-message>{{$message}}</x-error-message> @enderror
                </div>
            </div>

            <div class="form-group row">
                {{ html()->label('Note ')->class('col-sm-2 form-control-label')->for('note') }}
                <div class="col-sm-10">
                    <input type="text" wire:model="note" class="form-control">
                    @error('note') <x-error-message>{{$message}}</x-error-message> @enderror
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="form-group">
                        <div class="modal fade" id="confirmReplace" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Are you sure?</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="font-weight-bold">System will replace file:</div>
                                        @if(!empty($this->files))
                                            @foreach($this->files as $file)
                                                <div class="row">
                                                    <div class="col-12">{{ $file->getClientOriginalName() }}</div>
                                                </div>
                                            @endforeach
                                        @endif
                                    </div>
                                    <div class="modal-footer">
                                        <button wire:click="save" type="button" class="btn btn-primary" data-dismiss="modal">Yes</button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        @if(!empty($this->files) && !hasInvalidAdblockFile($this->files))
                            <button type="button" class="btn btn-primary"
                                    data-toggle="modal" data-target="#confirmReplace">
                                <i class="fas fa-plus-circle"></i>
                                Upload
                            </button>
                        @elseif(empty($this->files))
                            <button type="button" class="btn btn-secondary"
                                    disabled
                                    wire:click="save" >
                                <i class="fas fa-plus-circle"></i>
                                Upload
                            </button>
                        @else
                            <button type="button" class="btn btn-primary"
                                    wire:click="save" >
                                <i class="fas fa-plus-circle"></i>
                                Upload
                            </button>
                        @endif
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

@push('after-styles')
    <style>
        .tooltip {
            position: relative;
            display: inline-block;
            border-bottom: 1px dotted black;
        }

        .tooltip .tooltiptext {
            visibility: hidden;
            width: 120px;
            background-color: black;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px 0;

            /* Position the tooltip */
            position: absolute;
            z-index: 1;
        }

        .tooltip:hover .tooltiptext {
            visibility: visible;
        }
    </style>
@endpush


